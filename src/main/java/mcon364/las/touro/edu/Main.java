package mcon364.las.touro.edu;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        // call getGreeting with env variable that might exist
        var greeting1 = getGreeting("USERNAME");
        System.out.println(greeting1);

        // call getGreeting with env variable that doesn't exist
        var greeting2 = getGreeting("NO_SUCH_VAR");
        System.out.println(greeting2);

        // sample data for processValues
        var data = List.of(List.of(1, 23, 32), List.of(0, 55, 6), // skip rest of list when hits 0
                List.of(79, 8, 99), // stops processing when hits 99
                List.of(2, 98, 43));
        System.out.println("\nProcessing values");
        processValues(data);
    }

    /**
     * gets user name: environment var value wrapped in an Optional.
     *
     * @param envVarName the name of the environment var to get
     * @return Optional with the value if it exists, empty Optional otherwise
     */
    public static Optional<String> getUserName(String envVarName) {
        return Optional.ofNullable(System.getenv(envVarName));
    }

    /**
     * Creates personalized greeting based on username
     *
     * @param envVarName the environment var name to get
     * @return a greeting String with username if found, or generic greeting
     */
    public static String getGreeting(String envVarName) {
        var userName = getUserName(envVarName);

        if (userName.isPresent()) { // username exists
            var sb = new StringBuilder();
            sb.append("Hello, ").append(userName.get());
            return sb.toString();

        } else { // username doesn't exist
            var sb = new StringBuilder();
            sb.append("Hello, World");
            return sb.toString();
        }
    }

    /**
     * Processes a nested list of integers
     * - When 0: skips to next outer list
     * - When 99: exits all processing
     *
     * @param data nested list to process
     * @return int
     */
    public static int processValues(List<List<Integer>> data) {
        int counter = 0; // count total processed values

        outerloop:
        for (var innerList : data) { // outer loop goes through each list

            for (var value : innerList) { // inner list goes through each value in list

                if (value == 0) {
                    continue outerloop; // skip to next inner list
                }
                if (value == 99) {
                    break outerloop; // exit everything
                }

                System.out.println("Processing value " + value);
                counter++;
            }
        }
        return counter;
    }
}