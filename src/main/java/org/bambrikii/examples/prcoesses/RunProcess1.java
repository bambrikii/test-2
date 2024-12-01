package org.bambrikii.examples.prcoesses;

import lombok.SneakyThrows;

import java.io.*;

public class RunProcess1 {
    @SneakyThrows
    public static void main(String[] args) {
//        var ps = Runtime
//                .getRuntime()
//                .exec("./test-2/src/main/resources/proc1.sh");
        var pb = new ProcessBuilder("./test-2/src/main/resources/proc1.sh");
        var ps = pb.start();
        try (var os = ps.getInputStream();
             var bos = new BufferedInputStream(os);
             var isr = new InputStreamReader(bos);
             var br = new BufferedReader(isr);
             var os2 = ps.getOutputStream();
             var pw2 = new PrintWriter(os2);

             var eos = ps.getErrorStream();
             var bos2 = new BufferedInputStream(eos);
             var isr2 = new InputStreamReader(bos2);
             var br2 = new BufferedReader(isr2);
        ) {
            String outMsg;
            while ((outMsg = br.readLine()) != null) {
                pw2.write("something\n");
                System.out.println("out: " + outMsg);
            }
            String errMsg;
            while ((errMsg = br2.readLine()) != null) {
                System.out.println("error: " + errMsg);
            }
        }
    }
}
