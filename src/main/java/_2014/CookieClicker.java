package _2014;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CookieClicker {
    int MAXFARMS = 1000000;

    //c = number of cookies to purchase a Farm
    double c;
    //f = number of cookies/sec increase per Farm
    double f;
    //x = Total cookies necessary
    double x;

    public CookieClicker(double c, double f, double x) {
        this.c = c;
        this.f = f;
        this.x = x;
    }

    public double clickerBeta() {
        int curFarms = 0;
        double ans = 1e9;
        double time = 0;
        boolean done = false;

        while(!done || curFarms < MAXFARMS) {
            double tans = (x / (2 + (f*curFarms)) + time);
            if(tans <= ans) ans = tans;
            else done = true;
            time += c / (2 + f*curFarms);
            curFarms++;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("cookieclicker.input"));
        int T = in.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            double c = in.nextDouble();
            double f = in.nextDouble();
            double x = in.nextDouble();
//            System.out.println(String.format("c %f, f %f, x %f", c, f, x));
            CookieClicker cc = new CookieClicker(c, f, x);
            System.out.println(String.format("Case #%d: %f", testCase, cc.clickerBeta()));
        }
    }

    public double clickerAlpha() {
        return clickerAlpha(2.0, 0.0, x /2, 1);
    }

    //This did not work for recursive calls more than a few thousand
    //StackOverFlowError
    private double clickerAlpha(double cookiesPerSec, double elapsedTime, double maxElapsedTime, int currFarms) {
        if (elapsedTime > maxElapsedTime || currFarms >= MAXFARMS ) return elapsedTime;
        double noFarmElapsedTime = x /cookiesPerSec + elapsedTime;

        System.out.println(String.format("currFarms %d, noFarmElapsedTime %f, maxElapsedTime %f, elapsedTime %f, cookiesPerSec %f",
                currFarms, noFarmElapsedTime, maxElapsedTime, elapsedTime, cookiesPerSec));

        double nextFarmElapsedTime = clickerAlpha(cookiesPerSec+ f,
                elapsedTime + c /cookiesPerSec,
                noFarmElapsedTime<maxElapsedTime?noFarmElapsedTime:maxElapsedTime, ++currFarms);

        return noFarmElapsedTime < nextFarmElapsedTime ? noFarmElapsedTime : nextFarmElapsedTime;
    }

    public static void mainAlpha(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("cookieclicker.input"));
        int T = in.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            double C = in.nextDouble();
            double F = in.nextDouble();
            double X = in.nextDouble();
            System.out.println(String.format("c %f, f %f, x %f", C, F, X));
            CookieClicker cc = new CookieClicker(C, F, X);
            System.out.println(String.format("Case #%d: %f", testCase, cc.clickerAlpha()));
        }
    }
}
