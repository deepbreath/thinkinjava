package day10;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

public class Reflection {

    public static Set<String> analyze(Class<?> enumClass){
        System.out.print("======Analyzing "+enumClass+"======");

        System.out.print("Interfaces:");

        for (Type t:enumClass.getGenericInterfaces()){
            System.out.print(t);
        }

        System.out.print("Base:"+enumClass.getSuperclass());

        System.out.print("Methods: ");

        Set<String> methods=new TreeSet<>();

        for (Method me:enumClass.getMethods()){
            methods.add(me.getName());
        }

        System.out.print(methods);

        return methods;
    }






}
