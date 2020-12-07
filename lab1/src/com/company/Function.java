package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public abstract class Function implements Runnable {
    Pipe.SinkChannel sinkChannel;

    private final int x;
    private final boolean isTrialFunction;

    public Function(Pipe.SinkChannel sinkChannel, int x, boolean isTrialFunction) {
        this.sinkChannel = sinkChannel;
        this.x = x;
        this.isTrialFunction = isTrialFunction;
    }

    public abstract int compute() throws InterruptedException;

    private void write(int result) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(result);
        buffer.flip();
        while (buffer.hasRemaining()) {
            sinkChannel.write(buffer);
        }
    }

    @Override
    public void run() {
        try {
            int result = compute();
            write(result);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public boolean isTrialFunction() {
        return isTrialFunction;
    }
}
