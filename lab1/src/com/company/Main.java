package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Manager manager = new Manager(6, false);
        manager.compute();
    }
}
