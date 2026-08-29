package com.aryan.rest

object PatternMatching extends App {

  //Pattern matching is a mechanism for checking a value against a pattern. It is a more powerful version of the switch statement in other languages.

  val x: Any = "Scala"

  val result = x match {
    case 1 => "One"
    case "Scala" => "The Scala Programming Language"
    case _ => "Something else"
  }
  //PM is a EXPRESSION
  println(result) // Output: The Scala Programming Language


  //Pattern matching can also be used to deconstruct data structures,
  // such as case classes and tuples. It can also be used to match on types,
  // and to extract values from objects.

  case class Person(name: String, age: Int)

  val bob = Person("Bob", 54)


  val personGreeting = bob match {
    case Person(name, age) => s"Hi, my name is $name and I am $age years old"
    case _ => "I don't know who I am"
  }
  println(personGreeting) // Output: Hi, my name is Bob and I am 54 years old

  val aTuple = ("Bonjovi", "Rock")
  val tupleGreeting = aTuple match {
    case (band, genre) => s"My favorite band is $band and they play $genre music"
    case _ => "I don't know who my favorite band is"
  }
  println(tupleGreeting) // Output: My favorite band is Bonjovi and they play Rock music

  //decomposing lists
  val aList = List(1, 2, 3, 4, 5)
  val listGreeting = aList match {
    case List( 1, _, _, _*) => "The list starts with 1 and has 3 elements"
//    case List(1, _*) => "The list starts with 1 and has more than 4 elements"
    case _ => "The list does not start with 1"
  }
  println(listGreeting) // Output: The list starts with 1 and has more than 4 elements
}