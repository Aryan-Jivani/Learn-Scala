package com.aryan.rest

case class User(id : Long , name : String ,  email: String)
case class UpdateUserRequest(name: String,email: String)
case class ApiError(message: String)

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


  def addUser(currentUsers: List[User], newUser:User): List[User] = {
    currentUsers :+ newUser
  }
  def deleteUser(currentUsers: List[User], id: Long) : List[User] ={
    currentUsers.filterNot(_.id == id)
  }

  val usersAfterAdd = addUser(
    users,
    User(4L, "Aryan4", "test4@gmail.com")
  )

  val usersAfterDelete = deleteUser(usersAfterAdd, 2L)

  println(users)
  println(usersAfterAdd)
  println(usersAfterDelete)

  def updateUser(currentUsers: List[User], updatedUser:User): List[User] = {

    currentUsers.map( User =>{
      if(User.id==updatedUser.id) updatedUser else User
    })

  }

  val usersAfterUpdate = updateUser(
    users,
    User(2L, "Updated Aryan", "updated@gmail.com")
  )

  println(usersAfterUpdate)
  println(users)


  def addUserSafely(currentUsers: List[User], newUser: User): Either[String, List[User]] = {
    if (currentUsers.exists(_.id == newUser.id))
      Left("User with this ID already exists")
    else
      Right(currentUsers :+ newUser)
  }
  println(addUserSafely(users, User(5L, "Duplicate", "duplicate@gmail.com")))
  println(addUserSafely(users, User(4L, "Aryan4", "test4@gmail.com")))

}

