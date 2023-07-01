package Day1;

import java.util.ArrayList;
import java.util.List;

public class Elf implements Comparable<Elf> {
    private final List<Integer> calories = new ArrayList<>();

    public void addCalory(Integer calory) {
        this.calories.add(calory);
    }

    public int getTotalCalories() {
        return calories.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public int compareTo(Elf anotherElf) {
        return -Integer.compare(this.getTotalCalories(), anotherElf.getTotalCalories());
    }

    @Override
    public String toString() {
        return calories.toString();
    }
}
