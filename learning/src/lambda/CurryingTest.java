package lambda;

import java.util.function.Function;

public class CurryingTest {


    public static void main(String[] args) {
        Function<Integer, Function<Integer, Function<Integer, Integer>>>
                addNums = u -> v -> w -> u + v + w;

        int result = addNums.apply(2).apply(3).apply(4);
        System.out.println(result);
    }

}
