package com.aryan.rest

import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import UserJsonProtocol._
import org.apache.pekko.http.scaladsl.model.StatusCodes

import scala.concurrent.ExecutionContext
import scala.io.StdIn


object UserServer {

  val initialUsers: List[User] = List(
    User(1L, "Aryan1", "test1@gmail.com"),
    User(2L, "Aryan2", "test2@gmail.com"),
    User(3L, "Aryan3", "test3@gmail.com")
  )

  def createRoutes(users: List[User]): Route = {
    concat(
      path("users") {
        concat(
          get {
            complete(users)
          },
          post {
            entity(as[User]) { newUser =>
              complete(StatusCodes.Created, newUser)
            }
          }
        )
      },
      path("users" / LongNumber) { id =>
        concat(
          get {
            users.find(_.id == id) match {
              case Some(user) => complete(user)
              case None => complete(StatusCodes.NotFound, s"User with id $id not found")
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

                  complete(StatusCodes.OK, updatedUser)
                case None =>
                  complete(
                    StatusCodes.NotFound,
                    s"User with id $id not found"
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
                  s"User with id $id not found"
                )
            }
          }
        )
      }
    )
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

    println("Server running at http://localhost:8080/users")
    println("Press ENTER to stop")

    StdIn.readLine()

    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}
