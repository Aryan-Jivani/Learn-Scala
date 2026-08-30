package com.aryan.rest

import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import UserJsonProtocol._
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.server.{
  MalformedRequestContentRejection,
  RejectionHandler,
  Route
}
import org.slf4j.LoggerFactory
import scala.concurrent.ExecutionContext
import scala.io.StdIn
import scala.util.{Failure, Success}


object UserServer {
  private val logger = LoggerFactory.getLogger(this.getClass)

  val initialUsers: List[User] = List(
    User(1L, "Aryan1", "test1@gmail.com"),
    User(2L, "Aryan2", "test2@gmail.com"),
    User(3L, "Aryan3", "test3@gmail.com")
  )

  def validateUser(user: User): Either[String, User] = {
    val email = user.email.trim

    if (user.name.trim.isEmpty)
      Left("Name must not be empty")
    else if (email.isEmpty || !email.contains("@"))
      Left("Email must contain @")
    else
      Right(user)
  }

  private val rejectionHandler: RejectionHandler =
    RejectionHandler
      .newBuilder()
      .handle {
        case MalformedRequestContentRejection(_, _) =>
          complete(
            StatusCodes.BadRequest,
            ApiError("Invalid request body")
          )
      }
      .result()

  def createRoutes(users: List[User]): Route = {
    handleRejections(rejectionHandler) {
      concat(
        path("users") {
          concat(
            get {
              complete(users)
            },
            post {
              entity(as[User]) { newUser =>
                validateUser(newUser) match {
                  case Left(errorMessage) =>
                    complete(StatusCodes.BadRequest, ApiError(errorMessage))

                  case Right(validUser) =>
                    complete(StatusCodes.Created, validUser)
                }
              }
            }
          )
        },
        path("users" / LongNumber) { id =>
          concat(
            get {
              users.find(_.id == id) match {
                case Some(user) => complete(user)
                case None => complete(StatusCodes.NotFound, ApiError(s"User with id $id not found"))
              }
            },
            put {
              entity(as[UpdateUserRequest]) { request =>
                users.find(_.id == id) match {
                  case Some(existingUser) =>
                    val updatedUser = existingUser.copy(
                      name = request.name,
                      email = request.email
                    )

                    validateUser(updatedUser) match {
                      case Left(errorMessage) =>
                        complete(StatusCodes.BadRequest, ApiError(errorMessage))

                      case Right(validUser) =>
                        complete(StatusCodes.OK, validUser)
                    }

                  case None =>
                    complete(
                      StatusCodes.NotFound,
                      ApiError(s"User with id $id not found")
                    )
                }
              }
            },
            delete {
              users.find(_.id == id) match {
                case Some(_) =>
                  complete(StatusCodes.NoContent)

                case None =>
                  complete(
                    StatusCodes.NotFound,
                    ApiError(s"User with id $id not found")
                  )
              }
            }
          )
        }
      )
    }
  }

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] =
      ActorSystem(Behaviors.empty, "user-api")

    implicit val executionContext: ExecutionContext =
      system.executionContext

    val route = createRoutes(initialUsers)

    val bindingFuture =
      Http()
        .newServerAt("localhost", 8080)
        .bind(route)

    logger.info("Starting User API")

    bindingFuture.onComplete {
      case Success(binding) =>
        val address = binding.localAddress
        val apiUrl = s"http://${address.getHostString}:${address.getPort}/users"
        logger.info("User API started at {}", apiUrl)

      case Failure(exception) =>
        logger.error("Failed to start User API", exception)
    }

    logger.info("Press ENTER to stop")

    StdIn.readLine()

    logger.info("User API shutdown requested")

    bindingFuture
      .flatMap(_.unbind())
      .onComplete {
        case Success(_) =>
          logger.info("User API stopped")
          system.terminate()

        case Failure(exception) =>
          logger.error("Failed to stop User API cleanly", exception)
          system.terminate()
      }
  }

}
