package com.company;

import spos.lab1.demo.IntOps;

import java.nio.channels.Pipe;

public class FunctionG extends Function {

    public FunctionG(Pipe.SinkChannel sinkChannel, int x, boolean isTrialFunction) {
        super(sinkChannel, x, isTrialFunction);
    }

    @Override
    public int compute() throws InterruptedException {
        if (isTrialFunction()) {
            return IntOps.funcG(getX());
        }

        switch(getX()) {
            case 0:
                Thread.sleep(6000);
                return 5;
            case 1:
                Thread.sleep(3000);
                return 2;
            case 2:
                while (true) {}
            case 3:
                Thread.sleep(2000);
                return 0;
            case 4:
                while (true) {}
            case 5:
                Thread.sleep(2000);
                return 8;
            default:
                return 0;
        }
    }
}
