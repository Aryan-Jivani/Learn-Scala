package com.aryan.rest

case class User(id : Long , name : String ,  email: String)

object UserDomain extends App {

  val users = List(
    User(1L,"Aryan1","test1@gmail.com"),
    User(2L,"Aryan2","test2@gmail.com"),
    User(3L,"Aryan3","test3@gmail.com"),
//    User(4L,"Aryan4")
  )

  def findUserById(id: Long): Option[User] = {
    users.find(_.id == id)
    }

  def printUserResults(result: Option[User]) : Unit = {
    result match {
      case Some(User(id, name, email)) => println("User found with id: " + id + " and name: " + name + " and email: " + email)
      case None => println("User not Found")
    }
  }
  printUserResults(findUserById(1))
  printUserResults(findUserById(4))

}

