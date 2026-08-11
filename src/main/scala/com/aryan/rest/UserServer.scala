package com.aryan.rest


import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.http.scaladsl.server.Route
import org.apache.pekko.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext
import scala.io.StdIn


object UserServer {

  def main(args: Array[String]): Unit = {
    implicit val system: ActorSystem[Nothing] =
      ActorSystem(Behaviors.empty, "user-api")

    implicit val executionContext: ExecutionContext =
      system.executionContext

    val route: Route = {
      path("users") {
        get {
          complete("User end point is working")
        }
      }
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
