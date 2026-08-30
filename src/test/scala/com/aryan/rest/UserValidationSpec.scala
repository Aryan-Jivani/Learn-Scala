package com.aryan.rest

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class UserValidationSpec extends AnyWordSpec with Matchers {

  "User validation" should {

    "return Right for a valid user" in {
      val user = User(1L, "Valid User", "valid@example.com")
      UserServer.validateUser(user) shouldEqual (Right(user))
    }
    "return Left when the name is blank" in {
      val user =
        User(2L, "   ", "valid@example.com")

      UserServer.validateUser(user) shouldEqual
        Left("Name must not be empty")
    }
    "return Left when the email is invalid" in {
      val user =
        User(3L, "Valid User", "invalid-email")

      UserServer.validateUser(user) shouldEqual
        Left("Email must contain @")
    }
    "return Left when the email is blank" in {
      val user =
        User(4L, "Valid User", "   ")

      UserServer.validateUser(user) shouldEqual
        Left("Email must contain @")
    }
  }
}
