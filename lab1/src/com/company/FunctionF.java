package com.company;

import spos.lab1.demo.IntOps;

import java.nio.channels.Pipe;

public class FunctionF extends Function {

    public FunctionF(Pipe.SinkChannel sinkChannel, int x) {
        super(sinkChannel, x);
    }

    @Override
    public int compute() throws InterruptedException {
        return IntOps.funcF(getX());
    }
}
