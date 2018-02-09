package day1;

public class GenericMethod {

    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public static void main(String[] args){
        GenericMethod gm=new GenericMethod();
        gm.f(" ");
        gm.f(1);
        gm.f(1.0);
        gm.f('c');
        gm.f(gm);
    }

}
