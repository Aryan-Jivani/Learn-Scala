package com.aryan.rest

import spray.json.DefaultJsonProtocol
import spray.json.RootJsonFormat

object UserJsonProtocol extends DefaultJsonProtocol {

  implicit val userFormat: RootJsonFormat[User] =
    jsonFormat3(User.apply)
}
