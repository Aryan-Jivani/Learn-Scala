package com.aryan.rest

import scala.concurrent._
// import scala.util.* // for Try, Success, Failure works in scala 3
import scala.util._ // for Try, Success, Failure works in scala 2
import scala.concurrent.ExecutionContext.Implicits.global

object advanced extends App {
  /*
* Lazy Evaluations
* */
  lazy val x: Int = {
    println("Evaluating x")
    42
  }
  println("x initialized")
  println(x) // Evaluating x
  //useful in infinite collections


  /*
  * Option, Try collections
  * */

  def methodWhichCanReturnNull(): String = {
    if (scala.util.Random.nextBoolean()) "Hello" else null
  }

  val anOption = Option(methodWhichCanReturnNull()) // return Some("Hello") or None
  println(anOption)

  val stringProcessing = anOption match {
    case Some(string) => s"The string is: $string"
    case None => "The string is null"
  }
  println(stringProcessing)

  def methodWhichCanThrowException(): String = {
    if (Random.nextBoolean()) "Hello" else throw new RuntimeException("Something went wrong")
  }

  val aTry = Try(methodWhichCanThrowException()) // return Success("Hello") or Failure(exception)
  // a Try is a collection with either a Success or a Failure
  println(aTry)

  val anotherStringProcessing = aTry match {
    case Success(string) => s"The string is: $string"
    case Failure(exception) => s"The string is null because of exception: ${exception.getMessage}"
  }
  println(anotherStringProcessing)

  //map, flatMap, filter

  /*
  * Evaluation something on another thread using Future
    (asynchronous programming)
  * */

  val aFuture = Future({

    print("Loading...")
    Thread.sleep(1000)
    print("Finished!")
    42

  })
  Thread.sleep(2000) // wait for the future to complete
  println(aFuture.value) // Some(Success(42)) or Some(Failure(exception))

  /*
  * implicites basics
  * */
  //1. Implicit arguments
  def aMethodWithImplicitArgs(implicit x: Int): Int = x + 1

  implicit val myImplicitInt: Int = 46
  //  implicit val myImplicitInt2: Int = 47 //will not work because there can be only one implicit value of a given type in scope
  println(aMethodWithImplicitArgs) // 47
  println(aMethodWithImplicitArgs)

  //2. Implicit conversions
  //binds to the type of the value, not the name of the
  // so use it carefully, it can lead to unexpected behavior
  implicit class MyRichInt(value: Int) {
    def isEven: Boolean = value % 2 == 0
  }
  println(23.isEven) // false
}