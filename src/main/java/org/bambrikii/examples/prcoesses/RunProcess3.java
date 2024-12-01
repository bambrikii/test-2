package org.bambrikii.examples.prcoesses;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RunProcess3 {
    @SneakyThrows
    public static void main(String[] args) {
//        var ps = Runtime
//                .getRuntime()
//                .exec("./test-2/src/main/resources/proc1.sh");
        var pb = new ProcessBuilder("./test-2/src/main/resources/proc1.sh");
        var ps = pb.start();
        try (

                var pis = new PipedInputStream();
                var pos = new PipedOutputStream();
                var pisir = new InputStreamReader(pis);
                var pisbr = new BufferedReader(pisir);

                var os = ps.getInputStream();
                var eos = ps.getErrorStream();

                var seqstr = new SequenceInputStream(os, eos);
                var bos2 = new BufferedInputStream(seqstr);
                var isr2 = new InputStreamReader(bos2);
                var br2 = new BufferedReader(isr2);

                var os2 = ps.getOutputStream();
                var pw2 = new PrintWriter(os2);

        ) {
            pis.connect(pos);

            var thread1 = new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    String line;
                    while (
                            isAvailable()
                                    && !Thread.currentThread().isInterrupted()
                                    && (line = pisbr.readLine()) != null
                    ) {
                        System.out.println("pipe is, out: " + line);
                    }
                }

                private boolean isAvailable() {
                    try {
                        pis.available();
                        return true;
                    } catch (IOException ex) {
                        return false;
                    }
                }
            });
            thread1.start();

            String str;
            while ((str = br2.readLine()) != null) {
                String something = "something";
//                if (br2.ready()) {
                pw2.println(something);
                pos.write((something + "\n").getBytes(StandardCharsets.UTF_8));
//                }
                pos.write(str.getBytes(StandardCharsets.UTF_8));
            }
            pis.close();
//            pisir.close();
            ps.waitFor();
            ps.destroy();
//            thread1.join();
            System.out.println("close---");
            thread1.interrupt();
//            Thread.sleep(3000);

        }
    }
}
