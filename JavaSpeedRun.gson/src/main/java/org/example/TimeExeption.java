package org.example;

public class TimeExeption extends Exception {
    private int i = 0;

    public TimeExeption(int i, String message) {
        super(message);
        this.i = i;
    }

    @Override
    public String toString() {
        return "TimeException{" + "i=" + i + "}:" + this.getMessage();
    }
}
