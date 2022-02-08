package Scala.Composite

import Scala.Visitor.Visitor

trait Composite {

  def at(row: Int, col: String): String
  def columns(): Int
  def size(): Int
  def addDad(directory: Composite): Unit
  def accept(visitor: Visitor): Unit

}
