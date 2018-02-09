package test01;

public class Wildcards {
    static void rawArgs(Holder holder, Object arg){

        Object obj=holder.getValue();
    }

    static void unboundedArg(Holder<?> holder, Object args){
        Object obj=holder.getValue();
    }

    static <T> T exact1(Holder<T> holder) {
        T t = holder.getValue();
        return t;
    }

    static <T> T exact2(Holder<T> holder, T arg){
        holder.setValue(arg);
        T t=holder.getValue();
        return t;
    }

    static <T> T wildSubtype(Holder<? extends T> holder, T arg){
        T t=holder.getValue();
        return t;
    }

    static <T> void wildSupertype(Holder<? super T> holder, T arg){
        holder.setValue(arg);
        Object obj=holder.getValue();
    }

    public static void main(String[] args){

    }




}
