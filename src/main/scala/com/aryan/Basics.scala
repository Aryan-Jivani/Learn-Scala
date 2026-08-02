package com.aryan

object Basics extends App {

  //scala verison print
  println(s"Scala version: ${util.Properties.versionString}")

  val meaningOfLife: Int =  32//constants are immutable values that cannot be changed after they are defined

  //int boolean char doulbe float long short byte string are the primitive data types in scala

  val boolean=true //type mention is optional, compiler can infer the type of the variable based on the value assigned to it

  val astring = "Hello, World!" //string is a sequence of characters

  val aComposedString = s"$astring The meaning of life is $meaningOfLife" //string interpolation allows us to embed variables and expressions inside a string

  val d2ncp = "I" + "love" + "Scala" //string concatenation allows us to combine multiple strings into one

 // expression are structures that can be evaluated to produce a value, they can be simple or complex, and can include variables, literals, operators, and function calls

  val sum = 1 + 2 //simple expression

  val complexExpression = (1 + 2) * (3 - 4) / 5 //complex expression

  val temp1: Double = 5.0 / 2; //same as c++ data works 
  println(s"temp1: $temp1");

  println(complexExpression)

  //if- expression
  val ifexpression = if (meaningOfLife > 0) "Positive" else "Negative" //if expression evaluates a condition and returns a value based on the result

  print(ifexpression)

  val temp = myfuntion(5) //function call
  println(temp)

  def myfuntion(x: Int): Int = {
    x * 2
  } //function is a block of code that takes input, performs a specific task, and returns an output

  //unit type is used to indicate that a function does not return a meaningful value, similar to void in other programming languages
  //type of side effects, such as printing to the console or modifying a variable, is Unit
  print("asdf")

}
