package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;



public class ForEachLambda {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        nums.add(26);
        nums.add(53);
        nums.add(4);
        nums.add(95);
        nums.add(88);

        //1
        //nums.forEach(n -> System.out.println(n));


        //2
        //Consumer<Integer> method = (n -> System.out.println(n));
        //nums.forEach(method);

        //3
        //nums.forEach(System.out::println);


    }
}
