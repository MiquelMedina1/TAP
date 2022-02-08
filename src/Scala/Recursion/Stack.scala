package Scala.Recursion

/**
 * If condition is true, make the operation for all the elements in the list
 */
class Stack {
  def stack[A, B](condition: A => Boolean, operation: A => B, collection: List[A]): List[Any] = collection match {
    case Nil => Nil
    case x :: xs => if (condition(x)) operation(x) :: stack(condition, operation, xs) else x :: stack(condition, operation, xs)
  }
}
