package com.aryan.rest

import spray.json.DefaultJsonProtocol
import spray.json.RootJsonFormat

object UserJsonProtocol extends DefaultJsonProtocol {

  implicit val userFormat: RootJsonFormat[User] =
    jsonFormat3(User.apply)

  implicit val updateUserRequestFormat: RootJsonFormat[UpdateUserRequest] =
    jsonFormat2(UpdateUserRequest.apply)

  implicit val apiErrorFormat: RootJsonFormat[ApiError] =
    jsonFormat1(ApiError.apply)
}

