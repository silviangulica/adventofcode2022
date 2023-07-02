package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SolutionDay5 {
    private final Map<Integer, Stack<Character>> mapOfCrates = new HashMap<>();
    private final List<String[]> listOfInstructions = new ArrayList<>();
    private boolean needForP2 = false;
    public void solveP1() {
        boolean flagNeedToSwitch = false;
        try(BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day5.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                char[] lineInChars = line.toCharArray();

                if (line.equals("")) {
                    reverseStacks();
                    continue;
                }

                if(Character.isDigit(lineInChars[1])) {
                    flagNeedToSwitch = true;
                    continue;
                }

                if (!flagNeedToSwitch) {
                    int index = 1;
                    for (int i = 1; i < lineInChars.length; i+=4, index++) {
                        if (lineInChars[i] == ' ') continue;

                        if (mapOfCrates.containsKey(index)) {
                            mapOfCrates.get(index).add(lineInChars[i]);
                        } else {
                            mapOfCrates.put(index, new Stack<>());
                            mapOfCrates.get(index).add(lineInChars[i]);
                        }
                    }
                } else {

                    String[] instruction = line.replace("move", "").replace("from", "").replace("to", "").split(" ");
                    listOfInstructions.add(instruction);
                    // 1 -> how many?
                    // 3 -> from where?
                    // 5 -> to where?

                    int howMany     = Integer.parseInt(instruction[1]);
                    int fromWhere   = Integer.parseInt(instruction[3]);
                    int toWhere     = Integer.parseInt(instruction[5]);

                    if (!needForP2) {
                        for (int i = 1; i <= howMany; ++i) {
                            mapOfCrates.get(toWhere).add(mapOfCrates.get(fromWhere).pop());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (var value : mapOfCrates.values()) {
            sb.append(value.peek());
        }

        System.out.println(sb);
    }

    public void solveP2() {
        needForP2 = true;
        solveP1();
        for (var instruction : listOfInstructions) {
            int howMany     = Integer.parseInt(instruction[1]);
            int fromWhere   = Integer.parseInt(instruction[3]);
            int toWhere     = Integer.parseInt(instruction[5]);

            Stack<Character> stack = new Stack<>();
            for (int i = 1; i <= howMany; ++i) {
                stack.add(mapOfCrates.get(fromWhere).pop());
            }

            while(!stack.isEmpty()) {
                mapOfCrates.get(toWhere).add(stack.pop());
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (var e : mapOfCrates.values()) {
            stringBuilder.append(e.peek());
        }
        System.out.println(stringBuilder);
    }

    private void reverseStacks() {
        for (var value : mapOfCrates.values()) {
            Queue<Character> queue = new LinkedList<>();
            while(!value.isEmpty()) {
                queue.add(value.pop());
            }
            while(!queue.isEmpty()) {
                value.add(queue.poll());
            }
        }
    }
}
