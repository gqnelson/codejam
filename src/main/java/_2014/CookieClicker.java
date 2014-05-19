package _2014;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CookieClicker {
    //C = number of cookies to purchase a Farm
    double C;
    //F = number of cookies/sec increase per Farm
    double F;
    //X = Total cookies necessary
    double X;

    public CookieClicker(double c, double f, double x) {
        C = c;
        F = f;
        X = x;
    }

    public double clickerAlpha() {
        return clickerAlpha(2.0, 0.0, X/2);
    }

    private double clickerAlpha(double cookiesPerSec, double elapsedTime, double maxElapsedTime) {
        if (elapsedTime > maxElapsedTime) return elapsedTime;

        double noFarmElapsedTime = X/cookiesPerSec + elapsedTime;

        double nextFarmElapsedTime = clickerAlpha(cookiesPerSec+F, elapsedTime + C/cookiesPerSec, noFarmElapsedTime<maxElapsedTime?noFarmElapsedTime:maxElapsedTime);

        return noFarmElapsedTime < nextFarmElapsedTime ? noFarmElapsedTime : nextFarmElapsedTime;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(Paths.get("cookieclicker.input"));
        int T = in.nextInt();
        for (int testCase = 1; testCase <= T; testCase++) {
            double C = in.nextDouble();
            double F = in.nextDouble();
            double X = in.nextDouble();
            CookieClicker cc = new CookieClicker(C, F, X);
            System.out.println(String.format("Case #%d: %f", T, cc.clickerAlpha()));
        }
    }
}
