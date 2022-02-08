/**
 * @author Daniel Becerra
 * @author Miquel Medina
 */
package Test;


import Composite.Directory;
import Factory.*;
import Visitor.*;
import Proxy.Observer;
import Proxy.ObserverImpl;
import Proxy.TestInvocationHandler;
import Proxy.TestIf;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {

/*--------------------------------------------S------------------------------------------------------------------------------
		Test Factory Pattern
--------------------------------------------------------------------------------------------------------------------------		*/
        DataFrame df = new CSVFactory().read("cities.csv");
		System.out.println("Factory CSV");
        System.out.println("At: "+df.at(4, "LatS"));
        System.out.println("Iat: "+df.iat(1, 2));
        System.out.println("Columns: "+df.columns());
        System.out.println("Size: "+df.size());
        System.out.println("Descending Sort: "+Arrays.toString(df.sort("LatS", Comparator.reverseOrder())));
        System.out.println("Ascending Sort: "+Arrays.toString(df.sort("LatS", String::compareTo)));
        System.out.println("Query: "+Arrays.toString(df.query("LatD", (x) -> Integer.parseInt(x) < 42)));

		System.out.println("\nFactory JSON");
        DataFrame df2 = new JSONFactory().read("cities.json");
        System.out.println("At: "+df2.at(4, "LonM"));
        System.out.println("Iat: "+df2.iat(1, 2));
        System.out.println("Columns: "+df2.columns());
        System.out.println("Size: "+df2.size());
		System.out.println("Descending Sort: "+Arrays.toString(df2.sort("LatS", Comparator.reverseOrder())));
		System.out.println("Ascending Sort: "+Arrays.toString(df2.sort("LatS", Comparator.naturalOrder())));
		System.out.println("Query: "+Arrays.toString(df2.query("LatD", (x) -> Integer.parseInt(x) < 42)));

		System.out.println("\nFactory TXT");
        DataFrame df3 = new TXTFactory().read("ejemplo.txt");
        System.out.println("At: "+df3.at(2, "nombre"));
        System.out.println("Iat: "+df3.iat(1, 2));
        System.out.println("Columns: "+df3.columns());
        System.out.println("Query: "+Arrays.toString(df3.query("nombre", (x) -> x.equalsIgnoreCase("Daniel"))));
        System.out.println("Ascending Sort: "+Arrays.toString(df3.sort("nombre", Comparator.naturalOrder())));//(String a, String b) -> a.compareTo(b)
		System.out.println("Descending Sort: "+Arrays.toString(df3.sort("nombre", Comparator.reverseOrder())));//(String b, String a) -> a.compareTo(b)

		/*--------------------------------------------------------------------------------------------------------------------------
		Test Composite Pattern
--------------------------------------------------------------------------------------------------------------------------		*/

        DataFrame file = new CSVFactory().read("cities.csv");
        Directory dir = new Directory("CSV");
        dir.addChild(file);
        dir.addChild(file);
        dir.addChild(file);

        DataFrame file2 = new JSONFactory().read("cities.json");
        Directory dir2 = new Directory("JSON");
        dir2.addChild(file2);


        DataFrame file3 = new TXTFactory().read("ejemplo.txt");
        Directory dir3 = new Directory("TXT");
        dir3.addChild(file3);

        List<Directory> filelist = new ArrayList<>();
        filelist.add(dir);
        filelist.add(dir2);
        filelist.add(dir3);


        System.out.println("\nComposite");
        System.out.println("Composite at: " + filelist.get(0).at(1, "City"));
        System.out.println("Composite iat: " + filelist.get(0).iat(1, 4));
        System.out.println("Composite columns: " + filelist.get(2).columns());
        System.out.println("Composite size: " + filelist.get(1).size());
        System.out.println("Composite descending sort " + Arrays.toString(filelist.get(0).sort("City", Comparator.naturalOrder())));
        System.out.println("Composite ascending sort " + Arrays.toString(filelist.get(0).sort("City", Comparator.reverseOrder())));
        System.out.println("Composite query: "+Arrays.toString(filelist.get(0).query("LatD", (String x) -> Integer.parseInt(x)>30)));


        for (Directory i : filelist) {
            System.out.println(i.iat(4, 2));
        }

		/*--------------------------------------------------------------------------------------------------------------------------
		Test Visitor Pattern
--------------------------------------------------------------------------------------------------------------------------		*/

        System.out.println("\nVisitor");
        Maximum max = new Maximum("LatM");
        dir.accept(max);
        System.out.println("Maximum: " + max.getResults());

        Minimum min = new Minimum("LatD");
        dir.accept(min);
        System.out.println("Minimum: " + min.getResults());

        Average av = new Average("LatD");
        dir.accept(av);
        System.out.println("Average: " + av.getResults());

        Sum s = new Sum("LatD");
        dir.accept(s);
        System.out.println("Sum: " + s.getResults());

        /*--------------------------------------------------------------------------------------------------------------------------
                Test Proxy and Observer Pattern
        --------------------------------------------------------------------------------------------------------------------------		*/
        System.out.println("\nObserver and Proxy");
        Observer observer1 = new ObserverImpl(file);

        TestIf test1 = (TestIf) Proxy.newProxyInstance(TestIf.class.getClassLoader(),
                new Class<?>[]{TestIf.class},
                new TestInvocationHandler(file));
        System.out.println("Proxy at: " + test1.at(2, "LatD"));
        test1.attach(observer1);

    }


}



