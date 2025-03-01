package lambda;

import java.util.function.Function;

public class ClosureFuncTest {

    public static void main(String[] args) {
        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        Function<Integer, String> getIndianWeekDay = getWeekDay(weekDays);
        System.out.println(getIndianWeekDay.apply(6));

    }

    public static Function<Integer, String> getWeekDay(String[] weekDays){
        return index -> index >= 0 ? weekDays[index % 7] : null;
    }
}
