package software.ulpgc.kata5;

import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class FactorialCommand implements Command{
    private final static Output NullNumberOutput = nullNumberOutput();

    private Output factorial(String number) {
        Output result;
        if(number != null) {
            result = result(factorial(parseInt(number)));
        } else{
            result = NullNumberOutput;
        }
        return result;
    }

    private int factorial(int number) {
        return  IntStream.range(2, number + 1).reduce(1, (a, b) -> a * b);
    }

    private Output result(int factorial) {
        return new Output() {
            @Override
            public int response() {
                return 200;
            }

            @Override
            public String result() {
                return String.valueOf(factorial);
            }
        };
    }

    @Override
    public Output execute(Input input) {
        return factorial(input.get("number"));
    }


    private static Output nullNumberOutput() {
        return new Output() {
            @Override
            public int response() {
                return 0;
            }

            @Override
            public String result() {
                return null;
            }
        };
    }
}
