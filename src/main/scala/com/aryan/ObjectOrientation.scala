package com.aryan

object ObjectOrientation extends App{

  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }
  val anAnimal = new Animal
  println(anAnimal.creatureType)

  class Dog(name:String ) extends Animal {
    override val creatureType: String = "domestic"
    override def eat: Unit = println("crunch crunch")
  }
  val aDog = new Dog("Lassie")

  class Cat(name:String) extends Animal //constructor definition
  val aCat = new Cat("Tom")

    //constructor arguments are not fields, so they cannot be accessed outside the class unless they are defined as val or var
    // you need to put val or var in front of the constructor argument to make it a field

  //subtyping polymorphism
  val aDeclartionAnimal: Animal= new Dog("Spike")
  aDeclartionAnimal.eat //the most devived method will be called at runtime which is dog now but at compile time the type of the variable is Animal so it will only have access to the methods and fields defined in the Animal class

  //abstract class used to define a class that cannot be instantiated on its own, but can be extended by other classes. It can contain abstract methods (methods without an implementation) that must be implemented by subclasses, as well as concrete methods (methods with an implementation) that can be inherited by subclasses.

  abstract class WalkingAnimal {
    val hasLegs = true //by default public
    def walk: Unit
  }

  //interface == ultimate abstract type
 // traits are used to define a set of methods and fields that can be mixed into classes. They can contain both abstract and concrete members, and can be used to achieve multiple inheritance in Scala.
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // difference between abstract class and trait is that a class can extend only one abstract class but can mix in multiple traits. Also, traits are more flexible than abstract classes, as they can be mixed into any class, regardless
  // of its position in the class hierarchy, whereas abstract classes can only be extended by subclasses.

  //scala has single class inheritance and multiple trait we called that mixin
  class crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val aCroc = new crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation, can be used when a method takes a single parameter, allowing for more natural and readable code. It is often used in DSLs (domain-specific languages) and for operator overloading.

  //anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a dinosaur and I'm eating you, animal")
  }
  /*
  instead of
  class Carnivour_anonymous extends Carnivore {
    override def eat(animal: Animal): Unit = println("I'm a dinosaur and I'm eating you, animal")
  }
  val dinosaur = new Carnivour_anonymous
  * */


}

