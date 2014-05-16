package _2014;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllLines;

public class MagicTrick {
    public Set<Integer> rowNumbers(int row, int[][] input)
    {
        return Arrays.stream(input[row-1]).boxed().collect(Collectors.toSet());
    }

    public Set<Integer> intersection(Set one, Set two) {
        one.retainAll(two);
        return one;
    }

    public String magicTrick(int caseNumber, int row1, int[][] input1, int row2, int[][] input2) {
        Set<Integer> result1 = rowNumbers(row1, input1);
        Set<Integer> result2 = rowNumbers(row2, input2);
        Set intersect = intersection(result1, result2);
        String msg = null;

        switch (intersect.size()){
            case 0: msg = "Volunteer cheated!";
                break;
            case 1: msg = intersect.iterator().next().toString();
                break;
            default: msg = "Bad magician!";
        }

        return String.format("Case #%d: %s", caseNumber, msg);
    }

    public static void main(String[] args) {
        try {
            MagicTrick magicTrick = new MagicTrick();

            List<String> lines = Files.readAllLines(Paths.get("magictrick.input"), Charset.defaultCharset());
            int numberOfTestCases = Integer.valueOf(lines.get(0));
            int offset = 0;
            for (int i = 1; i <= numberOfTestCases; i++) {
                System.out.println(String.format("Test case # %s", i));
                int row1 = Integer.valueOf(lines.get(i + offset));
                int[][] input1 = getInput((i + offset + 1), lines);
                int row2 = Integer.valueOf(lines.get(i + offset + 5));
                int[][] input2 = getInput((i + offset + 6), lines);

                System.out.println(magicTrick.magicTrick(i, row1, input1, row2, input2));
                offset += 9;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[][] getInput(int i, List<String> lines) {
        //Convert 4 lines of integers into a multi dimensional int array
        int[][] input = new int[4][4];
        int index = 0;
        for (int j = i; j < i+4; j++) {
            input[index++] = convertStringToInt(lines.get(j));
        }
        return input;
    }

    private static int[] convertStringToInt(String line) {
        int row[] = new int[4];
        String[] split = line.split(" ");
        for (int i = 0; i < split.length; i++) {
            row[i] = Integer.valueOf(split[i]);
        }
        return row;
    }
}
