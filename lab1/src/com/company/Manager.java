package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Manager {
    private final int x;

    private Pipe pipeF;
    private Pipe pipeG;

    public Manager(int x) throws IOException {
        this.x = x;

        pipeF = Pipe.open();
        pipeF.source().configureBlocking(false);
        pipeF.sink().configureBlocking(false);

        pipeG = Pipe.open();
        pipeG.source().configureBlocking(false);
        pipeG.sink().configureBlocking(false);
    }
}
