package test01;

public class CaptureConversion {
    static <T> void f1(Holder<T> holder){
        T t=holder.getValue();
      System.out.println(t.getClass().getSimpleName());
    }
    static void f2(Holder<?> holder){
        f1(holder);
    }
    public static void main(String[] args){
        Holder raw=new Holder<Integer>(1);
        f2(raw);


    }
}
