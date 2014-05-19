package _2014;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

//https://code.google.com/codejam/contest/2974486/dashboard

public class MagicTrick {
    //Below is the second pass solution optimized for lines of code
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("magictrick.input"));
        int T = in.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {

            int rowNumber1 = in.nextInt()-1;
            HashSet<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
            for (int i = 0; i < 4; i++) { //Rows
                for (int j = 0; j < 4; j++) { //Columns
                    if(i==rowNumber1) set1.add(in.nextInt());
                    else in.next();
                }
            }

            int rowNumber2 = in.nextInt()-1;
            for (int i = 0; i < 4; i++) { //Rows
                for (int j = 0; j < 4; j++) { //Columns
                    if(i==rowNumber2) set2.add(in.nextInt());
                    else in.next();
                }
            }

            set1.retainAll(set2);

            String msg;
            switch (set1.size()){
                case 0: msg = "Volunteer cheated!";
                    break;
                case 1: msg = set1.iterator().next().toString();
                    break;
                default: msg = "Bad magician!";
            }

            System.out.println(String.format("Case #%d: %s", testCase, msg));
        }
    }


    //Below is my original solution that worked.
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

    public static void mainOrig(String[] args) {
        try {
            MagicTrick magicTrick = new MagicTrick();
            BufferedWriter br = Files.newBufferedWriter(Paths.get("./magictrick.output"), Charset.defaultCharset(), new OpenOption[] {StandardOpenOption.CREATE});

            List<String> lines = Files.readAllLines(Paths.get("magictrick.input"), Charset.defaultCharset());
            int numberOfTestCases = Integer.valueOf(lines.get(0));
            int offset = 0;
            for (int i = 1; i <= numberOfTestCases; i++) {
                System.out.println(String.format("Test case # %s", i));
                int row1 = Integer.valueOf(lines.get(i + offset));
                int[][] input1 = getInput((i + offset + 1), lines);
                int row2 = Integer.valueOf(lines.get(i + offset + 5));
                int[][] input2 = getInput((i + offset + 6), lines);

                br.write(magicTrick.magicTrick(i, row1, input1, row2, input2));
                br.write("\n");
                offset += 9;
            }
            br.flush();
            br.close();
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
