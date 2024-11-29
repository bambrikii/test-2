package org.bambrikii.examples.prcoesses;

import lombok.SneakyThrows;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class RunProcessWithCommonsExec1 {
    @SneakyThrows
    public static void main(String[] args) {
        var cl = new CommandLine("./test-2/src/main/resources/proc1.sh");

        try (ByteArrayOutputStream stdout = new ByteArrayOutputStream()) {
            PumpStreamHandler psh = new PumpStreamHandler(stdout);
            DefaultExecutor exec = DefaultExecutor.builder().get();
            exec.setStreamHandler(psh);
            exec.execute(cl);
            System.out.println(stdout.toString(Charset.defaultCharset()));
        }
    }
}
