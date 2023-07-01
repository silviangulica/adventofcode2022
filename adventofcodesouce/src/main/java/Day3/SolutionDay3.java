package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionDay3 {
    private final int lowerLetterRange = 97;
    private final int upperLetterRange = 26;

    public void solveP1() {
        int priorities = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day3.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {

                // Get the half of the line
                int len = line.length();

                // Do a split and create 2 list of chars
                List<Character> firstList = new ArrayList<>();
                List<Character> secondList = new ArrayList<>();

                for (int i = 0; i < len / 2; ++i) {
                    firstList.add(line.charAt(i));
                    secondList.add(line.charAt(i+len/2));
                }
                // Remove duplicates
                firstList = firstList.stream().distinct().collect(Collectors.toList());
                secondList = secondList.stream().distinct().collect(Collectors.toList());

                // Check for each first list, if one of the char appear in second one
                for (var element : firstList) {
                    if (secondList.contains(element)) {
                        priorities += getPriority(element);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("The sum: " + priorities);
    }

    private int getPriority(char item) {
        // For the lowerCase letters
        if ((int)item >= 97) {
            return ((int)item) - 96;
        }
        // For the upperCase letters
        else {
            return ((int)item) - 64 + 26;
        }
    }
}
