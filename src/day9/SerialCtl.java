package day9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerialCtl implements Serializable {

private String a;

private transient String b;

    public SerialCtl(String a, String b) {
        this.a = "Not Transient"+a;
        this.b = "Transient"+b;
    }

    @Override
    public String toString() {
        return "SerialCtl{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                '}';
    }

    private void writeObject(ObjectOutputStream stream)throws IOException{
        stream.defaultWriteObject();
        stream.writeObject(b);

    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {

        stream.defaultReadObject();

        b=(String)stream.readObject();

    }





}
