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

}