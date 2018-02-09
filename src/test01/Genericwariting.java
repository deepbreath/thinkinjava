package test01;

import java.util.ArrayList;
import java.util.List;

public class Genericwariting {
    static <T> void writeExact(List<T> list, T item){

        list.add(item);

    }

    static List<Apple> apples=new ArrayList<Apple>();
    static List<Fruit> fruits=new ArrayList<Fruit>();

    static void f1(){
        writeExact(apples,new Apple());
        //wirteExact(fruit,Apple()); //error
        //Incompatible types:found fruit ,required Apple

    }

    static <T> void writeWithWildCard(List<? super T> list,T item){
        list.add(item);
    }

    static void f2(){
        writeWithWildCard(apples,new Apple());
        writeWithWildCard(fruits,new Fruit());
    }


    public static void main(String[] args){
        f1();
        f2();
    }





}
