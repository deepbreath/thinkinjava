package test01;

/**
 * 与接口混合
 */
interface TimeStamped{long getStamp();}

class TimeStampedImp implements TimeStamped {

    private final long timeStamp;

   public TimeStampedImp() {
        timeStamp = System.currentTimeMillis();
    }

    @Override
    public long getStamp() {
        return timeStamp;
    }
}

interface SerialNumbered{long getSerialNumber();}

class SerialNumberImp implements SerialNumbered {

    private static long counter=1;

    private final long serialNumber=counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}

interface Basic{
    public void set(String val);
    public String get();
}

class Basicimp implements Basic {
    private String value;
    @Override
    public void set(String val) { value=val;}

    @Override
    public String get() { return value; }
}

class Mixin extends Basicimp implements TimeStamped, SerialNumbered {

    private TimeStamped timeStamped=new TimeStampedImp();
    private SerialNumbered serialNumbered=new SerialNumberImp();


    @Override
    public long getStamp() {
        return timeStamped.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumbered.getSerialNumber();
    }
}

public class Mixins{

    public static void main(String[] args){
        Mixin mixin1=new Mixin();
       Mixin mixin2=new Mixin();

       mixin1.set("test string1");
       mixin2.set("test string2");

       System.out.println(mixin1.get()+" "+mixin1.getStamp()+" "+mixin1.getSerialNumber());

        System.out.println(mixin2.get()+" "+mixin2.getStamp()+" "+mixin2.getSerialNumber());


    }



}
