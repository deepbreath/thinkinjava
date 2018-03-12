package day14;

/**
 * 哲学家就餐问题的一个经典死锁例证
 */
public class Chopstick {

    private boolean taken=false;

    public synchronized void take() throws InterruptedException{
        while (taken){
            wait();
            taken=true;
        }
    }
    public synchronized  void drop(){
        taken=false;
        notifyAll();
    }
}
