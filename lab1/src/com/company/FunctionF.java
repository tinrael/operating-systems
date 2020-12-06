package com.company;

import java.nio.channels.Pipe;

public class FunctionF extends Function {

    public FunctionF(Pipe.SinkChannel sinkChannel, int x) {
        super(sinkChannel, x);
    }

    @Override
    public void compute() {
        
    }
}
