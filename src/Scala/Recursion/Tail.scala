package Scala.Recursion

class Tail {

  /**
   * If condition is true, make the operation for all the elements in the list
   */
  def tail[A, B](condition: A => Boolean,  operation: A => B, collection: List[A], default: List[Any]): List[Any] = collection match{
    case Nil => default
    case x::xs => tail(condition, operation, xs, if(condition(x)) operation(x)::default else x::default)
  }
}
