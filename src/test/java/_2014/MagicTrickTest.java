package _2014;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

public class MagicTrickTest {

    @Test
    public void testRowNumbers() throws Exception {
        MagicTrick mt = new MagicTrick();

        int[][] input1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        Assert.assertEquals(mt.rowNumbers(1, input1), new HashSet(Arrays.asList(1, 2, 3, 4)));
        Assert.assertEquals(mt.rowNumbers(2, input1), new HashSet(Arrays.asList(5, 6, 7, 8)));
        Assert.assertEquals(mt.rowNumbers(2, input1), new HashSet(Arrays.asList(8, 7, 5, 6)));
        Assert.assertEquals(mt.rowNumbers(3, input1), new HashSet(Arrays.asList(9, 10, 11, 12)));
        Assert.assertEquals(mt.rowNumbers(4, input1), new HashSet(Arrays.asList(13, 14, 15, 16)));

    }

    @Test
    public void testIntersection() throws Exception {
        MagicTrick mt = new MagicTrick();
        Assert.assertEquals(mt.intersection(new HashSet<>(Arrays.asList(5, 6, 7, 8)), new HashSet<>(Arrays.asList(9, 10, 7, 12))), new HashSet<>(Arrays.asList(7)));
    }

    @Test
    public void testMagicTrick() {
        MagicTrick mt = new MagicTrick();
        int[][] input1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] input2 = {{1, 2, 5, 4}, {3, 11, 6, 15}, {9, 10, 7, 12}, {13, 14, 8, 16}};

        Assert.assertEquals(mt.magicTrick(1, 2, input1, 3, input2), "Case #1: 7");

        Assert.assertEquals(mt.magicTrick(2, 2, input1, 2, input1), "Case #2: Bad magician!");

        Assert.assertEquals(mt.magicTrick(3, 2, input1, 3, input1), "Case #3: Volunteer cheated!");

    }
}