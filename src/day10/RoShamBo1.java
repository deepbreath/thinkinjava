package day10;


import java.util.Random;

import static day10.Outcome.DRAW;
import static day10.Outcome.LOSE;
import static day10.Outcome.WIN;

interface Item{
    Outcome compete(Item item);
    Outcome eval(Paper paper);
    Outcome eval(Scissor scissor);
    Outcome eval(Rock r);
}

class Paper implements Item{
    @Override
    public Outcome compete(Item item) {return item.eval(this);}

    @Override
    public Outcome eval(Paper paper) {return DRAW;}

    @Override
    public Outcome eval(Scissor scissor) {return WIN;}

    @Override
    public Outcome eval(Rock r) { return LOSE;}

    @Override
    public String toString() { return "Paper"; }
}


class Scissor implements Item{
    @Override
    public Outcome compete(Item item) {return item.eval(this);}

    @Override
    public Outcome eval(Paper paper) {return WIN;}

    @Override
    public Outcome eval(Scissor scissor) { return DRAW; }

    @Override
    public Outcome eval(Rock r) { return WIN; }

    @Override
    public String toString() { return "Scissor"; }

}

class Rock implements Item{
    @Override
    public Outcome compete(Item item) {return item.eval(this);}

    @Override
    public Outcome eval(Paper paper) {return WIN;}

    @Override
    public Outcome eval(Scissor scissor) {return LOSE;}

    @Override
    public Outcome eval(Rock r) {return DRAW;}
    @Override
    public String toString() { return "Rtock"; }
}

public class RoShamBo1 {
    static final int SiZE=20;
    private static Random random=new Random(47);
    public static Item newItem(){
        switch (random.nextInt(3)){
            default:
            case 0:return new Scissor();
            case 1:return new Paper();
            case 2:return new Rock();
        }
    }

    public static void match(Item a,Item b){
        System.out.println(a+" vs."+b+": "+a.compete(b));
    }
    public static void main(String[] args){
        for (int i=0;i<SiZE;i++){
            match(newItem(),newItem());
        }
    }

}
