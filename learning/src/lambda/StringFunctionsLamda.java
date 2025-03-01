package lambda;



interface StringFunc {
    String run(String str);

}
public class StringFunctionsLamda {

    public static void main(String[] args) {

        StringFunc exclaim = s -> s + " !";
        StringFunc ask = s -> s + " ?";
        printFormatted("First Test", exclaim);
        printFormatted("Second Test", ask);



    }

    public static void printFormatted(String str, StringFunc func){

        String result = func.run(str);
        System.out.println(result);
    }
}
