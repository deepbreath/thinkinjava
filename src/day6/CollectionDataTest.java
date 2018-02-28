package day6;

import test01.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionDataTest {

    public static void main(String[] args){
        Set<String> set=new LinkedHashSet<>(new CollectionData<>(new Covernment(),15));
        set.addAll(CollectionData.list(new Covernment(),15));
        System.out.println(set);
        //元素顺序与插入顺序相投，是因为LinkedHashSet维护的是保持插入顺序的链接列表

    }

}
class Covernment implements Generator<String>{

    String[] foundation=("strange women lying in ponds " +"distributing swords is no basis for a system of "+"government").split(" ");

    private int index;
    @Override
    public String next() {
        return foundation[index++];
    }
}
