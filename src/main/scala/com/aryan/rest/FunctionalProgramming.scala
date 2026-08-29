package com.aryan.rest

object FunctionalProgramming extends App {

  //scala is oo

  class Person(name: String) {
    def apply(age: Int): Unit = println(s"My name is $name and I am $age years old")
  }
  val bob = new Person("Bob")
  bob.apply(54)
  bob(54)

  /*
  Scala runs on JVM
  Functional programming :
  - compose functions
  - pass functions as arguments
  - return functions as results
  - use functions as first class citizens

  Conclution : FunctionX = Function1, Function2, .... Function22
  22 is the maximum number of parameters a function can take in scala


  */

  val simpleincrementer = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  println(simpleincrementer.apply(23))
  simpleincrementer(23)
  //defined as a function
  //ALL SCALA FUNCTIONS ARE INSTANCES OF THE FUNCTIONX TRAIT

  val stingconcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(stingconcatenator("Hello, ", "World!"))
  println(stingconcatenator.apply("Hello, ", "World!"))


  //both are same
  //  val doubler: Int => Int = (x:Int) => x * 2
  //  val doubler: Function1[Int, Int] = (x: Int) => x * 2

  //syntax sugar for above function
  val stringconcatenator2: (String, String) => String = (a: String, b: String) => a + b
  println(stringconcatenator2.apply("Hello, ", "World!"))
  println(stringconcatenator2("Hello, ", "World!"))


  //higher order functions
  val aMappedList = List(1, 2, 3).map(x => x + 1) //map is a higher order function that takes a function as an argument and applies it to each element of the list
  println(aMappedList) //List(2, 3, 4)

  val flatMap = List(1, 2, 3).flatMap(x => List(x, 2*x)) //flatMap is a higher order function that takes a function as an argument and applies it to each element of the list, then flattens the result into a single list
  println(flatMap) //List(1, 2, 2, 3,

  val filterList = List(1, 2, 3, 4, 5).filter(x => x % 2 == 0) //filter is a higher order function that takes a function as an argument and applies it to each element of the list, then returns a new list with only the elements that satisfy the condition
  println(filterList) //List(2, 4)

  //even shorter syntax
  val aMappedList2 = List(1, 2, 3).map(_ + 1) //map is a higher order function that takes a function as an argument and applies
  println(aMappedList2) //List(2, 3, 4)

  val flatMap2 = List(1, 2, 3).flatMap(x => List(x, 2*x)) //flatMap is a higher order function that takes a function as an argument and applies it to each element of the list, then flattens the result into a single list
  println(flatMap2) //List(1, 2, 2, 3 ,6 )

  val filterList2 = List(1, 2, 3, 4, 5).filter(_ % 2 == 0) //filter is a higher order function that takes a function as an argument and applies it to each element of the list, then returns a new list with only the elements that satisfy the condition
  println(filterList2) //List(2, 4)

  // all paris between two lists
  val list1 = List(1, 2, 3)
  val list2 = List("a", "b", "c")
  val allPairs = list1.flatMap(x=> list2.map(x+"-"+_))
  println(allPairs) //List(1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c)

  //for comprehensions
  val forComprehension = for {
    x <- list1
    y <- list2
  }yield x + "-" + y
  println(forComprehension) //List(1a, 1b, 1c, 2a, 2b, 2c, 3a, 3b, 3c)
  //equivalent to the above flatMap and map combination

  /*Collection*/

  //list
  println("=============List=============")
  val aList = List(1, 2, 3, 4, 5)
  println(aList.head) //1
  println(aList.tail) //List(2, 3, 4, 5)
  val aprependentList = 0 +: aList //List(0, 1, 2, 3, 4, 5)
  val appendedList = aList :+ 6 //List(1, 2, 3, 4, 5, 6)
  val concatenatedList = aList ++ List(6, 7, 8) //List(1, 2, 3, 4, 5, 6, 7, 8)
  println(aprependentList)
  println(appendedList)
  println(concatenatedList)

  //sequence
  println("=============Sequence=============")
  // is like a list but it can be indexed and has a defined order,
  // it can be mutable or immutable, and it can be used to represent a collection of elements
  // that can be accessed by their index
  // index starts from 0
  val aSequence: Seq[Int] = Seq(1, 2, 3, 4, 5)
  println(aSequence) //List(1, 2, 3, 4, 5)
  println(aSequence.reverse) //List(5, 4, 3, 2, 1)
  println(aSequence(2)) //3
//  aSequence(2) = 10 //error: value update is not a member of Seq[Int] because Seq is immutable, we cannot change the value of an element in a sequence, we can only create a new sequence with the updated value
  println(aSequence ++ Seq(6, 7, 8)) //List(1, 2, 3, 4, 5, 6, 7, 8)
  println(aSequence.sorted) //List(1, 2, 3, 4, 5)

  //vector
  println("=============Vector=============")
  //Vector is a collection that is similar to a list but it is implemented as a tree,
  //which allows for faster access and updates to elements, especially for large collections.
  // It is also immutable and can be used to represent a collection of elements that can be
  // accessed by their index.
  val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5)
  println(aVector) //Vector(1, 2, 3, 4, 5)
  println(aVector.reverse) //Vector(5, 4, 3, 2, 1)
  println(aVector(2)) //3
  println(aVector ++ Vector(6, 7, 8)) //Vector(1, 2, 3, 4, 5, 6, 7, 8)
  println(aVector.sorted) //Vector(1, 2, 3, 4, 5)
//  aVector(2)=233 error

  //set
  println("=============Set=============")
  //Set is a collection that contains no duplicate elements, it is also immutable and can be
  // used to represent a collection of unique elements that can be accessed by their value.
  val aSet: Set[Int] = Set(1, 2, 3, 4, 5)
  println(aSet) //Set(1, 2, 3, 4, 5)
  println(aSet.contains(3)) //true
  println(aSet.contains(6)) //false
  val addset = aSet + 6 //Set(1, 2, 3, 4, 5, 6)
  val removeset = aSet - 3 //Set(1, 2, 4, 5)
  println(addset)
  println(removeset)

  //ranges
  println("=============Range=============")
  //Range is a collection that represents a sequence of numbers, it is also immutable and can
  // be used to represent a collection of numbers that can be accessed by their index.
  val aRange: Range = 1 to 50 //Range(1, 2 , 3, 4, 5)
  val aRange2: Range = 1 until 5 //Range(1, 2, 3, 4)
  println(aRange)
  println(aRange2)
  val twotimes = aRange.map(_ * 2).toList //Range(2, 4, 6, 8, 10)
  println(twotimes)

  //tuples
  println("=============Tuple=============")
  //Tuple is a collection that can contain elements of different types, it is also immutable and
  // can be used to represent a collection of elements that can be accessed by their index.
  val aTuple: (Int, String, Boolean) = (1, "Hello", true)
  println(aTuple) //(1,Hello,true)
  println(aTuple._1) //1
  println(aTuple._2) //Hello

  //maps
  println("=============Map=============")
  //Map is a collection that contains key-value pairs, it is also immutable and can be
  // used to represent a collection of elements that can be accessed by their key.
  val aMap: Map[String, Int] = Map("one" -> 1, "two" -> 2, "three" -> 3)
  println(aMap) //Map(one -> 1, two -> 2, three -> 3)
  println(aMap("one")) //1
  println(aMap.get("four")) //None
  println(aMap.getOrElse("four", 0)) //0


}
