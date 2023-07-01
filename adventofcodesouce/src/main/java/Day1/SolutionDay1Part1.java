package Day1;

import Solution.Solution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class SolutionDay1Part1 implements Solution {
    Queue<Elf> sortedElfsCalories = new PriorityQueue<>();
    // How many calories is carrying Elf with the mos calories?
    public int solve() {
        Elf currentElf = new Elf();

        // 1. Read the data line by line, if we encounter a blank-line, then a new Elf will pe spawned and added
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day1.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 0) {
                    sortedElfsCalories.add(currentElf);
                    currentElf = new Elf();
                } else {
                    currentElf.addCalory(Integer.parseInt(line));
                }
            }
            // Add the remained Elf to the queue
            sortedElfsCalories.add(currentElf);
        } catch (IOException e ){
            e.printStackTrace();
        }
        // 2. Return the first Elf in the PriorityQueue
        Elf biggestElf = sortedElfsCalories.peek();
        assert biggestElf != null;
        return biggestElf.getTotalCalories();
    }

    public Elf getAndRemoveFirstElf() {
        return sortedElfsCalories.poll();
    }

    public Queue<Elf> getSortedElfsCalories() {
        return this.sortedElfsCalories;
    }
}
