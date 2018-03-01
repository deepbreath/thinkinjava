package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CountedString {
    private static List<String> created=new ArrayList<>();
    private String s;
    private int id=0;

    public CountedString(String str){
        s=str;
        created.add(s);
        for (String s2:created){
            if (s2.equals(s)){
                id++;
            }
        }
    }

    @Override
    public String toString() {
        return "CountedString{" + "s='" + s + '\''+ ", id=" + id +"hashCode()"+hashCode()+"}";
    }


    @Override
    public int hashCode() {
       int result=17;
       result=37*result+s.hashCode();
       result=37*result+id;
       return result;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountedString && s.equals(((CountedString)obj).s) && id==((CountedString)obj).id;
    }
    public static void main(String[] args) {



    }
}
