package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay2 {
    private final Map<String, Integer> mapOfPoints = new HashMap<>();
    private final Map<String, String> mapOfWhatCanBeat = new HashMap<>();

    private final Map<String, String> mapOfWhatCanLose = new HashMap<>();
    private final Map<String, String> mapOfWhatIsDraw = new HashMap<>();

    private final Map<String, String> mapToMoves = new HashMap<>();


    public SolutionDay2() {
        // Put the points in the map
        mapOfPoints.put("R", 1);
        mapOfPoints.put("P", 2);
        mapOfPoints.put("S", 3);

        // Put the enemies in the map
        mapOfWhatCanBeat.put("A", "S");
        mapOfWhatCanBeat.put("B", "R");
        mapOfWhatCanBeat.put("C", "P");

        mapOfWhatCanLose.put("A", "P");
        mapOfWhatCanLose.put("B", "S");
        mapOfWhatCanLose.put("C", "R");

        mapOfWhatIsDraw.put("A", "R");
        mapOfWhatIsDraw.put("B", "P");
        mapOfWhatIsDraw.put("C", "S");
    }

    public void solveP1() {
        int score = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day2.txt"));

            boolean needToLose = false;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");
                if (mapToMoves.containsKey(splitLine[1])) {
                    String player = mapToMoves.get(splitLine[1]);
                    // Check if there is a loss
                    if (mapOfWhatCanBeat.get(splitLine[0]).equals(player)) {
                        score += mapOfPoints.get(player);
                        // Check if there is a win
                    } else if (mapOfWhatCanLose.get(splitLine[0]).equals(player)) {
                        score += mapOfPoints.get(player) + 6;
                    } else {
                        score += mapOfPoints.get(player) + 3;
                    }
                } else if (needToLose) {
                    // A loss is 0 pct
                    mapToMoves.put(splitLine[1], mapOfWhatCanBeat.get(splitLine[0]));
                    needToLose = false;
                    score += mapOfPoints.get(mapToMoves.get(splitLine[1]));
                    mapToMoves.put(returnMissingKey(), returnMissingValue());
                }
                else {
                    // A win is 6 pct
                    mapToMoves.put(splitLine[1], mapOfWhatCanLose.get(splitLine[0]));
                    needToLose = true;
                    score += mapOfPoints.get(mapToMoves.get(splitLine[1])) + 6;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Score: " + score);
        System.out.println(mapToMoves);
    }

    public void solveP2() {
        int score = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("inputs/input_day2.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] splitLine = line.split(" ");

                // X -> we need to lose
                if (splitLine[1].equals("X")) {
                    score += mapOfPoints.get(mapOfWhatCanBeat.get(splitLine[0]));
                    continue;
                }

                // Z -> we need to win
                if (splitLine[1].equals("Z")) {
                    score += mapOfPoints.get(mapOfWhatCanLose.get(splitLine[0])) + 6;
                    continue;
                }

                // Y -> we need to draw
                if (splitLine[1].equals("Y")) {
                    score += mapOfPoints.get(mapOfWhatIsDraw.get(splitLine[0])) + 3;
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Points with strategy 2: " + score);
    }

    public String returnMissingKey() {
        if (!mapToMoves.containsKey("X")) return "X";
        if (!mapToMoves.containsKey("Y")) return "Y";
        if (!mapToMoves.containsKey("Z")) return "Z";
        return "";
    }

    public String returnMissingValue() {
        if (!mapToMoves.containsValue("P")) return "P";
        if (!mapToMoves.containsValue("S")) return "S";
        if (!mapToMoves.containsValue("R")) return "R";
        return "";
    }
}
