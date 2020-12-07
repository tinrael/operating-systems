package com.company;

import spos.lab1.demo.IntOps;

import java.nio.channels.Pipe;

public class FunctionF extends Function {

    public FunctionF(Pipe.SinkChannel sinkChannel, int x, boolean isTrialFunction) {
        super(sinkChannel, x, isTrialFunction);

    }

    @Override
    public int compute() throws InterruptedException {
        if (isTrialFunction()) {
            return IntOps.funcF(getX());
        }

        switch(getX()) {
            case 0:
                Thread.sleep(3000);
                return 9;
            case 1:
                Thread.sleep(6000);
                return 6;
            case 2:
                Thread.sleep(2000);
                return 0;
            case 3:
                while (true) {}
            case 4:
                Thread.sleep(2000);
                return 7;
            case 5:
                while (true) {}
            default:
                return 0;
        }
    }
}
