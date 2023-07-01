package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolutionDay4 {
    public void solveP1() {
        int pairs = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day4.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] positions = line.split(",");

                int firstRightValue = Integer.parseInt(positions[0].split("-")[1]);
                int secondRightValue = Integer.parseInt(positions[1].split("-")[1]);

                int firstLeftValue = Integer.parseInt(positions[0].split("-")[0]);
                int secondLeftValue = Integer.parseInt(positions[1].split("-")[0]);

                // Case I
                if (firstLeftValue <= secondLeftValue && firstRightValue >= secondRightValue) {
                    pairs++;
                    continue;
                }

                // Case II
                if (secondLeftValue <= firstLeftValue && secondRightValue >= firstRightValue) {
                    pairs++;
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of pairs: " + pairs);
    }

    public void solveP2() {
        int pairs = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day4.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] positions = line.split(",");

                int firstRightValue = Integer.parseInt(positions[0].split("-")[1]);
                int secondRightValue = Integer.parseInt(positions[1].split("-")[1]);

                int firstLeftValue = Integer.parseInt(positions[0].split("-")[0]);
                int secondLeftValue = Integer.parseInt(positions[1].split("-")[0]);

                List<Integer> firstPairRange = IntStream.rangeClosed(firstLeftValue, firstRightValue).boxed().toList();
                List<Integer> secondPairRange = IntStream.rangeClosed(secondLeftValue, secondRightValue).boxed().toList();

                boolean containsElements = !Collections.disjoint(firstPairRange, secondPairRange);

                if (containsElements) {
                    pairs++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of pairs: " + pairs);
    }
}
