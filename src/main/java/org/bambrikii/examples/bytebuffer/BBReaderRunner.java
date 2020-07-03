package org.bambrikii.examples.bytebuffer;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class BBReaderRunner {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Path filePath = FileSystems.getDefault().getPath(BBReaderRunner.class.getResource("file1.txt").getFile());
        SeekableByteChannel byteChannel = Files.newByteChannel(filePath);
        ByteBuffer byteBuffer = ByteBuffer.allocate(30);
        Charset charset = StandardCharsets.UTF_8;
        while (byteChannel.read(byteBuffer) > 0) {
            byteBuffer.rewind();
            System.out.print(charset.decode(byteBuffer));
            byteBuffer.flip();
        }
    }
}
