package day4;

import test01.Generator;

import java.util.ArrayList;

public class CollectionData<T> extends ArrayList<T> {
    public CollectionData(Generator<T> generator,int quantity){
        for(int i=0;i<quantity;i++){
            add(generator.next());
        }
    }

    public static <T> CollectionData<T> list(Generator<T> gen,int quantity){
        return new CollectionData<T>(gen,quantity);
    }
}
