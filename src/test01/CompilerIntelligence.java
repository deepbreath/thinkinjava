package test01;

import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {
    public static void main(String[] args){
        List<? extends Fruit> flis= Arrays.asList(new Apple());
        //no warning
        Apple a=(Apple)flis.get(0);
        //Argument is Object
        flis.contains(new Apple());
        //Argument is 'Object'
        flis.indexOf(new Apple());
    }
}
