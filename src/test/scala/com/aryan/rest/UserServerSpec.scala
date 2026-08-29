package com.aryan.rest

import com.aryan.rest.UserJsonProtocol._
import org.apache.pekko.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import org.apache.pekko.http.scaladsl.model.StatusCodes
import org.apache.pekko.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class UserServerSpec
  extends AnyWordSpec
    with Matchers
    with ScalatestRouteTest {

  val testUsers: List[User] = List(
    User(10L, "Test User", "test@example.com")
  )

  val route = UserServer.createRoutes(testUsers)

  "User routes" should {

    "return all users for GET /users" in {

      Get("/users") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[List[User]] shouldEqual testUsers
      }
    }
  }
}