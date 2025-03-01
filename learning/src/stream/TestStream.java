package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        
        
        List<String> names = List.of("Alice", "Bob", "Charlie");
        Stream<String> nameStream = names.stream();
        
        Stream<String> filteredNames = names.stream().filter(name -> name.startsWith("A"));
        System.out.println(filteredNames);
        
        List<Integer> nameLengths = names.stream().map(String::length).collect(Collectors.toList());
        System.out.println(nameLengths);
        
        List<String> sortedNames = names.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedNames);
        
        names.stream().forEach(System.out::println);
        
        List<String> uppercaseNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(uppercaseNames);
        
        
        long count = names.stream().count();
        System.out.println(count);
        
        
        Optional<String> firstName = names.stream().findFirst();
        System.out.println(firstName.orElse("No name found"));
        
        Optional<String> anyName = names.stream().findAny();
        System.out.println(anyName.orElse("No name found"));
        
        boolean allStartWithA = names.stream().allMatch(name -> name.startsWith("A"));
        boolean anyStartWithA = names.stream().anyMatch(name -> name.startsWith("A"));
        boolean noneStartWithA = names.stream().noneMatch(name -> name.startsWith("A"));
        System.out.println(allStartWithA);
        System.out.println(anyStartWithA);
        System.out.println(noneStartWithA);
        
        
        List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5);
        List<Integer> uniqueNumbers = numbers.stream().distinct().collect(Collectors.toList());
        
        System.out.println(uniqueNumbers);
        
        List<Integer> limitedNumbers = numbers.stream().limit(3).collect(Collectors.toList());
        System.out.println(limitedNumbers);
        
        List<Integer> skipNumbers = numbers.stream().skip(3).collect(Collectors.toList());
        
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        
        
        String[] arr = {"Java", "Python", "C++"};
        Stream<String> arrStream = Arrays.stream(arr);
        
        
        Stream<Integer> numberStream = Stream.of(1, 2, 3, 4, 5);
        
        
        Stream<Integer> infiniteStream = Stream.iterate(1, n -> n + 1).limit(10);
        infiniteStream.forEach(System.out::println);
        
        Stream<Double> randomStream = Stream.generate(Math::random).limit(5);
        randomStream.forEach(System.out::print);
        
        
        // Finds all even numbers squares them and sorts the result
        List<Integer> exp = List.of(5, 8, 12, 3, 9, 2, 6);
        List<Integer> result = exp
                .stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .sorted()
                .collect(Collectors.toList());
        
        System.out.println(result);
        
        
        List<List<String>> nestedList = List.of(List.of("Alice", "Bob"), List.of("Charlie", "David"));
        
        List<Stream<String>> mappedList = nestedList.stream().map(List::stream).collect(Collectors.toList());
        
        System.out.println(mappedList); // The result is a nested stream which is not useful in most cases.
        
        
        List<String> flattenedList = nestedList
                .stream()
                .flatMap(List::stream) // Flattens List<Stream<String>> to a single Stream<String>.
                .collect(Collectors.toList());
        
        System.out.println(flattenedList);
        
    }
}
