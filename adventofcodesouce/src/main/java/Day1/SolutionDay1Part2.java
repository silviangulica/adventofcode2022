package Day1;

import Solution.Solution;

import java.util.Queue;

public class SolutionDay1Part2 implements Solution {
    Queue<Elf> elves;
    // How many calories are carrying in total the first 3 Elves?
    public int solve() {
        if (elves == null) return 0;
        int firstElfCalories    = elves.poll().getTotalCalories();
        int secondElfCalories   = elves.poll().getTotalCalories();
        int thirdElfCalories    = elves.poll().getTotalCalories();

        return firstElfCalories + secondElfCalories + thirdElfCalories;
    }

    public void setElves(Queue<Elf> elves) {
        this.elves = elves;
    }
}
