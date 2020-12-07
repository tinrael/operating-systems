package com.company;

import java.util.Scanner;

public class KeyConsoleListener implements Runnable {

    @Override
    public void run() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter 'q' to cancel the computation.");

        String input = keyboard.nextLine();
        while (!"q".equals(input)) {
            input = keyboard.nextLine();
        }
    }
}
