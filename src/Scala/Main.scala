package Scala
import Factory.{CSVFactory, DataFrame}
import Composite.DirectoryScala
import Recursion.{Stack, Tail}
import Visitor.{CounterVisitor, FilterVisitor}

import scala.jdk.CollectionConverters._


object Main{
    def main(args: Array[String]): Unit = {

      val fileCSV: DataFrame = new CSVFactory().read("cities.csv")
      val dirCSV: DirectoryScala = new DirectoryScala("dirCSV")
      dirCSV.addChild(fileCSV)

      val d = new DirectoryScala("a")
      dirCSV.addChild(d)
      d.addChild(fileCSV)

      /*--------------------------------------------------------------------------------------------------------------------------
          Test Composite Pattern
      --------------------------------------------------------------------------------------------------------------------------		*/

      println("\nComposite")
      print("at:"+dirCSV.at(1,"LatD"))
      print("\ncolumns: "+dirCSV.columns())
      print("\nsize: "+dirCSV.size())

      /*--------------------------------------------------------------------------------------------------------------------------
		Test Visitor Filter
--------------------------------------------------------------------------------------------------------------------------		*/

      val fil = new FilterVisitor("LatD", a => a.toInt > 46)
      dirCSV.accept(fil)
      println("\n\nFilter Visitor")
      println("FilterVisitor:\t" + fil.list)

      /*--------------------------------------------------------------------------------------------------------------------------
		Test Filter Counter
--------------------------------------------------------------------------------------------------------------------------		*/

      val counter = new CounterVisitor()
      dirCSV.accept(counter)
      println("\nCounter Visitor")
      println("Num of files:\t" + counter.numFiles + "\nNum of directories:\t" + counter.numDirectories)

      /*--------------------------------------------------------------------------------------------------------------------------
		Test Recursion Stack
--------------------------------------------------------------------------------------------------------------------------		*/

      var ls = fileCSV.getColumn("LatD").asScala.toList
      val stack = new Stack()
      println("\nRecursion Stack (int > 40 to float)")
      println(stack.stack[String, Float](a => a.toFloat > 40, a => a.toFloat.round.toFloat, ls))

      /*--------------------------------------------------------------------------------------------------------------------------
		Test Recursion Tail
--------------------------------------------------------------------------------------------------------------------------		*/
      val tail = new Tail()
      ls = fileCSV.getColumn("City").asScala.toList
      val list = List()
      println("\nRecursion Tail (replace Reading)")
      println(tail.tail[String, String](a => a.contains("Reading"), a => a.replace("Reading", "____"), ls, list))
    }


}

