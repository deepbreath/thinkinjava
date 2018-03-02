package day8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int BSIZE=1024;
    public static void main(String[] args) throws Exception{

        FileChannel fc=new FileOutputStream("/Users/chenyucheng/Documents/等待天黑.txt").getChannel();

        fc.write(ByteBuffer.wrap("Some text".getBytes()));

        fc.close();

        fc=new RandomAccessFile("/Users/chenyucheng/Documents/等待天黑.txt","rw").getChannel();

        fc.position(fc.size());

        fc.write(ByteBuffer.wrap("Some more".getBytes()));

        fc.close();

        fc=new FileInputStream("/Users/chenyucheng/Documents/等待天黑.txt").getChannel();

        ByteBuffer buff=ByteBuffer.allocate(BSIZE);

        fc.read(buff);

        buff.flip();

        while (buff.hasRemaining()){
            System.out.print((char)buff.get());
        }





    }
}
