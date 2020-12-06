package com.company;

import spos.lab1.demo.IntOps;

import java.nio.channels.Pipe;

public class FunctionG extends Function {

    public FunctionG(Pipe.SinkChannel sinkChannel, int x) {
        super(sinkChannel, x);
    }

    @Override
    public int compute() throws InterruptedException {
        return IntOps.funcG(getX());
    }
}
