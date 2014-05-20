package _2014;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CookieClickerTest {
    @Test public void testClickerAlpha()
    {
        CookieClicker cc = new CookieClicker(500, 4, 400.0);
        Assert.assertEquals(cc.clickerAlpha(), 400.0/2);

        cc = new CookieClicker(500, 4, 600.0);
        Assert.assertEquals(cc.clickerAlpha(), 600.0/2);

        cc = new CookieClicker(500, 4, 900.0);
        Assert.assertEquals(cc.clickerAlpha(), (500.0/2 + 900.0/6));


        cc = new CookieClicker(500, 4, 1000.0);
        Assert.assertEquals(cc.clickerAlpha(), (500.0/2 + 1000.0/6));

        cc = new CookieClicker(500, 4, 1500.0);
        Assert.assertEquals(cc.clickerAlpha(), (500.0/2 + 500.0/6 + 1500.0/10));

        cc = new CookieClicker(500, 4, 2000.0);
        Assert.assertEquals(cc.clickerAlpha(), (500.0/2 + 500.0/6 + 500.0/10 + 2000.0/14));
    }

    @Test
    public void testSampleInput1() {
        CookieClicker cc = new CookieClicker(30, 1.0, 2.0);
        Assert.assertEquals(cc.clickerAlpha(), 1.0);
    }

    @Test
    public void testSampleInput2() {
        CookieClicker cc = new CookieClicker(30, 2.0, 100.0);
        Assert.assertEquals(cc.clickerAlpha(), 39.1666667, 0.0000001);
    }

    @Test
    public void testSampleInput3() {
        CookieClicker cc = new CookieClicker(30.5, 3.14159, 1999.19990);
        Assert.assertEquals(cc.clickerAlpha(), 63.9680013, 0.0000001);
    }

    @Test
    public void testSampleInput4() {
        CookieClicker cc = new CookieClicker(500.0, 4.0, 2000.0);
        Assert.assertEquals(cc.clickerAlpha(), 526.1904762, 0.0000001);
    }

    @Test
    public void testSampleInput5() {
        CookieClicker cc = new CookieClicker(1.033970, 99.876140, 99999.032770);
        Assert.assertEquals(cc.clickerAlpha(), 526.1904762, 0.0000001);
    }

    @Test
    public void testCalculate() {
        double C = 1.03397;
        double F = 99.876140;
        double X = 99999.032770;

        double cookiesPerSec = 2;
        double elapsedTime = 0.0;
        double noFarmElapsedTime = elapsedTime;
        double maxElapsedTime = X/cookiesPerSec;

        int numberOfLoops = 1;
        while (elapsedTime < maxElapsedTime) {
            noFarmElapsedTime = X/cookiesPerSec + elapsedTime;
            elapsedTime += C/cookiesPerSec;
            cookiesPerSec += F;
            System.out.println(String.format("# loops %d, noFarmElapsedTime %f, maxElapsedTime %f, elapsedTime %f, cookiesPerSec %f",
                    numberOfLoops, noFarmElapsedTime, maxElapsedTime, elapsedTime, cookiesPerSec));
            numberOfLoops++;
        }

    }

    @Test
    public void testDoublePointerB() {
        int MAXFARMS = 1000000;

        double c = 1.03397;
        double f = 99.876140;
        double x = 99999.032770;

        int curFarms = 0;
        double ans = 1e9;
        double time = 0;
        boolean done = false;

        while(!done || curFarms < MAXFARMS) {
            double tans = (x / (2 + f*curFarms)) + time;
            System.out.printf("tans %f, curFarms %d, time %f\n", tans, curFarms, time);
            if(tans <= ans) ans = tans;
            else done = true;
            time += c / (2 + f*curFarms);
            curFarms++;
        }
        System.out.printf("Case #%d: %.7f%n", 5, ans+1e-9);
    }

}