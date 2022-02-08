package Scala.Composite


import Scala.Visitor.Visitor

import scala.collection.mutable.ListBuffer

class DirectoryScala(val name: String) extends Composite {

  val children: ListBuffer[Composite] = new ListBuffer[Composite]()
  var parent: Composite = _

  /**
   *
   * @param row row (int)
   * @param col column (String)
   * @return the value of a single item (row) and column label (name)
   */
  override def at(row: Int, col: String): String = {
    val z = for (child <- children) yield child.at(row, col)
    z.foldLeft(" ")(_+_)
  }

  /*
    override def at(row: Int, col: String): String = {
      var z =""
        for (child <- children)
          z += (" ")+child.at(row, col)
      z
    }*/

  /**
   * Columns Method
   *
   * @return number of labels
   */
  override def columns(): Int = {
    var col = 0
    for (child <- children) {
      col += child.columns
    }
    col
  }

  /**
   * Size Method
   *
   * @return number of items
   */
  override def size(): Int = {
    var size = 0
    for (child <- children) {
      size += child.size
    }
    size
  }

  /**
   * addChild Method that adds a new Composite child
   * @param child  child
   */
  def addChild(child: Composite): Unit={
    children += child
    child.addDad(this)
  }

  /**
   * addDad Method assignates a parent
   *
   * @param directory Composite
   */
   def addDad(directory: Composite): Unit = {
    parent=directory
  }

  /**
   * accept Method (Scala)
   * @param visitor visitor
   */
  override def accept(visitor: Visitor): Unit = {
    visitor.visit(this)}
}
