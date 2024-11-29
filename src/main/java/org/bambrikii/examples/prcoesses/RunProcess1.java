package org.bambrikii.examples.prcoesses;

import lombok.SneakyThrows;

import java.io.*;

public class RunProcess1 {
    @SneakyThrows
    public static void main(String[] args) {
        var ps = Runtime
                .getRuntime()
                .exec("./test-2/src/main/resources/proc1.sh");
        try (var os = ps.getInputStream();
             var bos = new BufferedInputStream(os);
             var isr = new InputStreamReader(bos);
             var br = new BufferedReader(isr);
             var os2 = ps.getOutputStream();
             var pw2 = new PrintWriter(os2);
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                pw2.write("something\n");
                System.out.println(line);
            }
        }
    }
}
