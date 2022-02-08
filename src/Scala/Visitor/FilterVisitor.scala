package Scala.Visitor

import Factory.DataFrame

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
import scala.collection.mutable.ListBuffer

class FilterVisitor(col: String, filter : String => Boolean) extends Visitor {

  var list = new ListBuffer[String]()

  /**
   * visit Method that visits and filter a dataframe
   * @param df dataframe
   */
  override def visit(df: DataFrame): Unit = {
    val numCol = df.head.indexOf(col)
    list.addAll(df.structure.get(numCol).filter(filter))
  }

}
