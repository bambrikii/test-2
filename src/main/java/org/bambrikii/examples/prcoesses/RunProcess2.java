package org.bambrikii.examples.prcoesses;

import lombok.SneakyThrows;

import java.io.*;

public class RunProcess2 {
    @SneakyThrows
    public static void main(String[] args) {
//        var ps = Runtime
//                .getRuntime()
//                .exec("./test-2/src/main/resources/proc1.sh");
        var pb = new ProcessBuilder("./test-2/src/main/resources/proc1.sh");
        var ps = pb.start();
        try (
                var os = ps.getInputStream();
                var eos = ps.getErrorStream();

                var seqstr = new SequenceInputStream(os, eos);
                var bos2 = new BufferedInputStream(seqstr);
                var isr2 = new InputStreamReader(bos2);
                var br2 = new BufferedReader(isr2);

                var os2 = ps.getOutputStream();
                var pw2 = new PrintWriter(os2);

        ) {
            String outMsg;
            while ((outMsg = br2.readLine()) != null) {
                pw2.println("something");
                System.out.println("out: " + outMsg);
            }
        }
    }
}
