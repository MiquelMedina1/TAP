package Composite;

import Visitor.Visitor;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface AComponent  {
     void accept(Visitor v);

	 List<AComponent> toList();

	 void setParent(AComponent parent);

     String getName();

    String at(int i, String city);

    String iat(int i, int j);

    int columns();

    int size();

    Object[] sort(String city, Comparator<String> type);

    Object[] query(String col, Predicate<String> condition);
}
