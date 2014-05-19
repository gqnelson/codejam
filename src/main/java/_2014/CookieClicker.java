package _2014;

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
        System.out.println(String.format("noFarmElapsedTime %f, cookiesPerSec %f, elapsedTime %f, maxElapsedTime %f", noFarmElapsedTime, cookiesPerSec, elapsedTime, maxElapsedTime));

        double nextFarmElapsedTime = clickerAlpha(cookiesPerSec+F, elapsedTime + C/cookiesPerSec, noFarmElapsedTime<maxElapsedTime?noFarmElapsedTime:maxElapsedTime);

        return noFarmElapsedTime < nextFarmElapsedTime ? noFarmElapsedTime : nextFarmElapsedTime;
    }
}
