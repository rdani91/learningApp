package hu.rozsa.daniel.learningapplication.eighth;

public class MyMathProvider {


    public double add(double a1, double a2) {
        return a1 + a2;
    }

    public double abs(double a) {
        return a >= 0 ? a : 0 - a;
    }

    public double divide(double dividend, double divider) {
        if(divider == 0){
            throw new IllegalArgumentException("divider can't be zero");
        }
        return dividend / divider;
    }
}
