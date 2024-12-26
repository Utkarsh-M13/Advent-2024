import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            int answer = 0;
            String regex = "mul\\(\\d+,\\d+\\)";
            String dont = "don't\\(\\)";
            String doo = "do\\(\\)";
            int mul = 0;

            String input = "";
            String line = br.readLine();
            while (line != null) {
                input = input.concat(line);
                line = br.readLine();
            }


            String[] segments = input.split(dont, 2);
            String remainder = segments[1];
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(segments[0]);

            // Find all matches
            while (matcher.find()) {
                String match = matcher.group(); // Get the matched substring
//                System.out.println("Found: " + match);
                String[] numbers = match.replaceAll("[^\\d+,]", "").split(",");
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[1]);
                answer += num1 * num2;
                mul++;
            }

            while (true) {
                segments = remainder.split(doo, 2);
                segments = segments[1].split(dont, 2);
                if (segments.length < 2) {
                    segments = remainder.split(doo, 2);
                    matcher = pattern.matcher(segments[1]);
                    while (matcher.find()) {
                        String match = matcher.group(); // Get the matched substring
                        String[] numbers = match.replaceAll("[^\\d+,]", "").split(",");
                        int num1 = Integer.parseInt(numbers[0]);
                        int num2 = Integer.parseInt(numbers[1]);
                        answer += num1 * num2;
                        mul++;
                    }
                    break;
                }
                remainder = segments[1];
                matcher = pattern.matcher(segments[0]);

                // Find all matches
                while (matcher.find()) {
                    String match = matcher.group(); // Get the matched substring
                    String[] numbers = match.replaceAll("[^\\d+,]", "").split(",");
                    int num1 = Integer.parseInt(numbers[0]);
                    int num2 = Integer.parseInt(numbers[1]);
                    answer += num1 * num2;
                    mul++;
                }
            }

            // Compile the regex

            System.out.println("Answer: " + answer);
            System.out.println("Mul: " + mul);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}