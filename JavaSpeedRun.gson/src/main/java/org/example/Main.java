package org.example;

import java.util.Scanner;

public class Main {
    static String pathSave = "src/main/resources/clocks.json";

    public static void main(String[] args) {

        ShopGUI app = new ShopGUI(pathSave);
        app.setVisible(true);


    }
}


