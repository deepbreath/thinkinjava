package day11;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {

    public static void trackUseCases(List<Integer> useCaes,Class<?> cl){
        for (Method m:cl.getDeclaredMethods()){
            UseCase uc=m.getAnnotation(UseCase.class);
            if (uc!=null){
                System.out.println("Found Use Case:"+uc.id()+" "+uc.description());
                useCaes.remove(new Integer(uc.id()));
            }
        }

        for (int i:useCaes){
            System.out.println("warning:Misssing use case-"+i);
        }

    }
    public static void main(String[] args){
        List<Integer> useCases=new ArrayList<>();
        Collections.addAll(useCases,47,48,49,50);
        trackUseCases(useCases,PasswordUtils.class);

    }
}
