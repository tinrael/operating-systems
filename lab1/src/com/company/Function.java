package com.company;

import java.nio.channels.Pipe;

public abstract class Function implements Runnable {
    Pipe.SinkChannel sinkChannel;

    private final int x;

    public Function(Pipe.SinkChannel sinkChannel, int x) {
        this.sinkChannel = sinkChannel;
        this.x = x;
    }

    public abstract void compute();

    @Override
    public void run() {
        compute();
    }

    public int getX() {
        return x;
    }
}
