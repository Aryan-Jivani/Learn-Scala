package com.aryan.rest


import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.aryan.rest.UserJsonProtocol._
import org.apache.pekko.http.scaladsl.model.StatusCodes

import scala.concurrent.ExecutionContext
import scala.io.StdIn


object UserServer {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] =
      ActorSystem(Behaviors.empty, "user-api")

    implicit val executionContext: ExecutionContext =
      system.executionContext

    val users = List(
      User(1L, "Aryan1", "test1@gmail.com"),
      User(2L, "Aryan2", "test2@gmail.com"),
      User(3L, "Aryan3", "test3@gmail.com")
    )

    val route: Route = {
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
          get {
            users.find(_.id == id) match {
              case Some(user) => complete(user)
              case None       => complete(StatusCodes.NotFound, s"User with id $id not found")
            }
          }
        }
      )
    }

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
