package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelismTest {

    public static void main(String[] args) {
        Integer[] intArray = {1,2,3,4,5};
        List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));

        System.out.println("List using serial stream :");
        intList
                .stream()
                .forEach(e -> System.out.println(e + ""));
        System.out.println(" ");


        System.out.println("List using parallel stream :");
        intList
                .parallelStream()
                .forEach(e -> System.out.println(e + ""));
        System.out.println(" ");


        System.out.println("List using another parallel stream :");
        intList
                .stream()
                .parallel()
                .forEach(e -> System.out.println(e + ""));
        System.out.println(" ");

        System.out.println("List using parallel but ordered :");
        intList
                .parallelStream()
                .forEachOrdered(e -> System.out.println(e + ""));
        System.out.println(" ");
    }
}
