package lambda;

import java.util.stream.IntStream;

public class ReducingTest {

    public static void main(String[] args) {
        // 1 * 2 * 3 * 4 = 24
        int product = IntStream.range(1, 5)
                .reduce((num1, num2) -> num1 * num2)
                .orElse(-1);

        // 1 + 2 + 3 + 4 + 5 = 10
        int sum = IntStream.range(1, 5).sum();

        System.out.println(product);
        System.out.println(sum);
    }
}
