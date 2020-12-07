package com.company;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class Manager {
    private final int x;

    private Pipe pipeF;
    private Pipe pipeG;

    public Manager(int x) throws IOException {
        if (x > 6 || x < 1) {
            System.out.println("[Manager]: Incorrect value of 'x'.");
            System.exit(0);
        }
        this.x = x - 1;

        pipeF = Pipe.open();
        pipeF.source().configureBlocking(false);
        pipeF.sink().configureBlocking(false);

        pipeG = Pipe.open();
        pipeG.source().configureBlocking(false);
        pipeG.sink().configureBlocking(false);
    }

    public void compute() throws IOException {
        ByteBuffer bufferF = ByteBuffer.allocate(4);
        ByteBuffer bufferG = ByteBuffer.allocate(4);

        Pipe.SourceChannel sourceChannelF = pipeF.source();
        Pipe.SourceChannel sourceChannelG = pipeG.source();

        Thread keyConsoleListenerThread = new Thread(new KeyConsoleListener());
        keyConsoleListenerThread.start();

        (new Thread(new FunctionF(pipeF.sink(), x))).start();
        (new Thread(new FunctionG(pipeG.sink(), x))).start();

        int resultF = 0;
        boolean isFComputed = false;
        int resultG = 0;
        boolean isGComputed = false;

        while (keyConsoleListenerThread.isAlive()) {
            if (bufferF.hasRemaining()) {
                sourceChannelF.read(bufferF);
                if (!bufferF.hasRemaining()) {
                    bufferF.flip();
                    resultF = bufferF.getInt();
                    isFComputed = true;
                    System.out.println("[Manager]: f(" + x + ") = " + resultF + ".");
                    if (resultF == 0) {
                        System.out.println("[Manager]: The final result can be computed immediately, because f(" + x + ") = 0.");
                        System.out.println("[Manager]: f(" + x + ") * g(" + x + ") = 0.");
                        System.exit(0);
                    }
                }
            }

            if (bufferG.hasRemaining()) {
                sourceChannelG.read(bufferG);
                if (!bufferG.hasRemaining()) {
                    bufferG.flip();
                    resultG = bufferG.getInt();
                    isGComputed = true;
                    System.out.println("[Manager]: g(" + x + ") = " + resultG + ".");
                    if (resultG == 0) {
                        System.out.println("[Manager]: The final result can be computed immediately, because g(" + x + ") = 0.");
                        System.out.println("[Manager]: f(" + x + ") * g(" + x + ") = 0.");
                        System.exit(0);
                    }
                }
            }

            if (!bufferF.hasRemaining() && !bufferG.hasRemaining()) {
                System.out.println("[Manager]: f(" + x + ") * " + "g(" + x + ") = " + (resultF * resultG) + ".");
                System.exit(0);
            }
        }

        System.out.println("[Manager]: The computation was canceled by the user.");
        System.out.print("[Manager]: The final result can not be computed as ");
        if (!isFComputed && !isGComputed) {
            System.out.println("f(" + x + ") and g(" + x + ") are not computed.");
        } else if (!isFComputed) {
            System.out.println("f(" + x + ") is not computed.");
        } else if (!isGComputed){
            System.out.println("g(" + x + ") is not computed.");
        }
        System.exit(0);
    }
}
