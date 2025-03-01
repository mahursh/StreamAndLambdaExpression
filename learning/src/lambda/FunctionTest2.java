package lambda;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionTest2 {
    
    public static void main(String[] args) {
        Function<Integer, Integer> multiply = t -> t * 3;
        Function<Integer, Integer> add = t -> t + 3;
        Function<Integer, Integer> firstMultiplyThenAdd = multiply.compose(add);
        Function<Integer, Integer> firstAddThenMultiply = multiply.andThen(add);
        
        System.out.println(firstMultiplyThenAdd.apply(3));
        System.out.println(firstAddThenMultiply.apply(3));
    }
}
