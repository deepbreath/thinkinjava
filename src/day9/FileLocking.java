package day9;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

public class FileLocking {

    public static void main(String[] agrs) throws Exception{
        FileOutputStream fos=new FileOutputStream("file.txt");
        FileLock fl=fos.getChannel().tryLock();
        if(fl !=null){
            System.out.println("Locked File");
            TimeUnit.MICROSECONDS.sleep(100);
            fl.release();
            System.out.println("Released Lock");
        }
        fos.close();
    }
}
