package Proxy;


import java.util.Comparator;
import java.util.function.Predicate;

public interface TestIf {
    String at(int row, String col);
    String iat(int row, int col);
    int columns();
    int size();
    Object[] sort(String city, Comparator<String> type);
    Object[] query(String col, Predicate<String> condition);
    void attach(Observer observer);
}
