package day7;

public class StringHashCode {

    public static void main(String[] args){
        //hashcode的生成是根据内容所以会有重复
        String[] hellos="Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());

    }
}
