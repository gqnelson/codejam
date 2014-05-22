package _2014;

import org.testng.annotations.Test;

public class MineSweeperTest {


    @Test
    public void testOneClick() {
        MineSweeper.oneClick(5, 5, 23);
        MineSweeper.oneClick(3, 1, 1);
        MineSweeper.oneClick(2, 2, 1);
        MineSweeper.oneClick(4, 7, 3);
        MineSweeper.oneClick(10, 10, 82);
        MineSweeper.oneClick(10, 10, 90);
        MineSweeper.oneClick(4, 7, 3);
        MineSweeper.oneClick(4, 7, 14);
        MineSweeper.oneClick(4, 7, 24);
        System.out.println("Done");
    }

    @Test
    public void testDoubleClick() {
        MineSweeper.oneClick(5, 4, 15);
        MineSweeper.doubleClick(5, 4, 15);
        System.out.println("Done");
    }
}
