package test01;

class Fruit{}

class Apple extends Fruit {}

class Jonathan extends Apple {}

class Orange extends Fruit {}

public class CovariantArrays {
    public static void main(String[] args){
        Fruit[] fruits=new Apple[0];
        fruits[0] =new Apple();


    }




}

