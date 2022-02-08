package Scala.Visitor

import Factory.DataFrame
import Scala.Composite.DirectoryScala

class CounterVisitor extends Visitor {
  var numDirectories:Int = 0
  var numFiles:Int = 0

  /**
   * visit Method that counts the directories visited
   * @param dir Directory
   */
  override def visit(dir: DirectoryScala): Unit = {
    numDirectories += 1
    super.visit(dir)
  }

  /**
   * visit Method that counts the dataframes visited
   * @param dir dataframe
   */
  override def visit(dir: DataFrame): Unit =  numFiles += 1
}
