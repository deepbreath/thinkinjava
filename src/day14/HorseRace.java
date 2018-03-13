package day14;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class HorseRace {



}
class Horse implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private int strides=0;
    private static Random random=new Random(47);
    private static CyclicBarrier barrier;
    public Horse(CyclicBarrier b){ barrier=b;}
    public synchronized int getStrides(){return strides;}

    @Override
    public void run() {



    }
}
