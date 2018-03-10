package day12;


import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Car{
    private boolean waxOn=false;
    public synchronized void waxed(){
        //ready to buff
        waxOn=true;
        notifyAll();
    }
    public synchronized void buffed(){
        //ready for another coat of wax
        waxOn=false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException{
        while (waxOn=false){
            wait();
        }
    }
    public synchronized void waitForBuffing()throws InterruptedException{
        while (waxOn=false){
            wait();
        }
    }
}

class WaxOn implements Runnable{
    private Car car;
    public WaxOn(Car c){
        car=c;
    }
    @Override
    public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("Wax On!");
                    TimeUnit.MICROSECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending wax On task");
        }
    }

    class WaxOff implements Runnable{
       private Car car;

        public WaxOff(Car car) {
            this.car = car;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()){
                    car.waitForWaxing();
                    System.out.println("Wax off!");
                    TimeUnit.MICROSECONDS.sleep(2000);
                    car.buffed();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Ending Wax off task");

        }
    }


public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car=new Car();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
