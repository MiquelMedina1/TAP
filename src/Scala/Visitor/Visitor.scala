package Scala.Visitor

import Factory.DataFrame
import Scala.Composite.DirectoryScala

abstract class Visitor{

  def visit(file: DataFrame): Unit

  /**
   * Method that is visiting the files
   *
   * @param dir directory
   */
  def visit(dir: DirectoryScala): Unit = {
    for (elem <- dir.children) {
      elem.accept(this)

    }
  }


}
