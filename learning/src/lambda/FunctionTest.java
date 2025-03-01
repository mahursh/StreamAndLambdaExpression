package lambda;

import java.util.function.Predicate;

public class FunctionTest {
    public static void main(String[] args) {
        Predicate<String> hasName = text -> text.contains("name");
        Predicate<String> hasPassword = text -> text.contains("password");
        Predicate<String> hasBothPasswordAndName = hasName.and(hasPassword);

        String test = "nameAndpassword";

        System.out.println(hasBothPasswordAndName.test(test));

    }
}

