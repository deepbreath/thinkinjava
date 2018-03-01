package day6;

import test01.Generator;

public class Generated {
    public static<T> T[] array(T[] a, Generator<T> gen){
        return new CollectionData<T>(gen,a.length).toArray(a);
    }
    //创建新的排列
    public static <T> T[] array(Class<T> type,Generator<T> gen,int size){
        @SuppressWarnings("unchecked")
        T[] a=(T[])java.lang.reflect.Array.newInstance(type,size);

        return new CollectionData<T>(gen,size).toArray(a);

    }
}
