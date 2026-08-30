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
    "return one user for GET /users/{id}" in {
      Get("/users/10") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[User] shouldEqual testUsers.head
      }
    }
    "return 404 for an unknown user ID" in {
      Get("/users/99") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
        responseAs[String] shouldEqual "User with id 99 not found"
      }
    }
    "return the created user for POST /users" in {
      val newUser =
        User(20L, "New User", "new@example.com")

      Post("/users", newUser) ~> route ~> check {
        status shouldEqual StatusCodes.Created
        responseAs[User] shouldEqual newUser
      }
    }
    "return the created user for PUT /users/10" in {
      val updatedUser =
        UpdateUserRequest( "New User", "new@example.com")
      val expectedUser = User(10L, updatedUser.name, updatedUser.email)
      Put("/users/10", updatedUser) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[User] shouldEqual expectedUser
      }
    }
    "return 404 for PUT /users/100 when the user does not exist" in {
      val updatedUser =
        UpdateUserRequest( "New User", "new@example.com")
      Put("/users/100", updatedUser) ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
        responseAs[String] shouldEqual s"User with id 100 not found"
      }
    }
    "delete an existing user for DELETE /users/10" in {
      Delete("/users/10") ~> route ~> check {
        status shouldEqual StatusCodes.NoContent
      }
    }
    "return 404 for DELETE /users/100 when the user does not exist" in {
      Delete("/users/100") ~> route ~> check {
        status shouldEqual StatusCodes.NotFound
        responseAs[String] shouldEqual "User with id 100 not found"
      }
    }
    "return 400 for POST /users when the name is blank" in {
      val invalidUser =
        User(20L, "   ", "new@example.com")

      Post("/users", invalidUser) ~> route ~> check {
        status shouldEqual StatusCodes.BadRequest
        responseAs[String] shouldEqual "Name must not be empty"
      }
    }
    "return 400 for POST /users when the email is invalid" in {
      val invalidUser =
        User(21L, "Valid Name", "invalid-email")

      Post("/users", invalidUser) ~> route ~> check {
        status shouldEqual StatusCodes.BadRequest
        responseAs[String] shouldEqual "Email must contain @"
      }
    }
  }
}
