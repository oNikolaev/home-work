package com.sbrf.reboot;

public class Calculator {

    public int getAddition(int subtrahend_1, int subtrahend_2) {
        return (subtrahend_1 + subtrahend_2);
    }

    public int getSubtraction(int substraction, int subtracted) {
        return (substraction - subtracted);
    }

    public int getMultiplication(int factor_1, int factor_2) {
        return (factor_1 * factor_2);
    }

    public int getPow(int number, int extent) {
        return ((int)Math.pow(number, extent));
    }

    public int getDivision(int divisible, int divisor) {
        return (divisible / divisor);
    }

    public int getAbs(int number) {
        return (Math.abs(number));
    }

    public String getSystemInfo() {
        return "Calculator v1";
    }
}
