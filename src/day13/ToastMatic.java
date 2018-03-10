package day13;


import java.util.Random;
import java.util.concurrent.*;

class Toast{
    public enum Status{DRY,BUTTERED,JAMMD};
    private Status status=Status.DRY;
    private final int id;
    public Toast(int id) {
        this.id = id;
    }
    public  void butter(){status=Status.BUTTERED;}
    public void jam(){status=Status.JAMMD;}
    public Status getStatus() {return status;}

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "status=" + status +
                ", id=" + id +
                '}';
    }
}
class ToastQueue extends LinkedBlockingQueue<Toast>{}

class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count=0;
    private Random rand=new Random(47);

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(500));
                //Make toast
                Toast t=new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }

        }catch (Exception e){
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}


class Butter implements Runnable{

   private ToastQueue dryQueue,butterQueue;

    public Butter(ToastQueue dryQueue, ToastQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                //Blocks until next piece of toast is available;
                Toast t=dryQueue.take();
                t.butter();
                System.out.println(t);
                butterQueue.put(t);

            }

        }catch (Exception e){
            System.out.println("Butter interrupted");
        }
        System.out.println("Butterer off");
    }
}

class Jammer implements Runnable{

    private ToastQueue butteredQueue,finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }
    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t=butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (Exception e){
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Eater implements Runnable{

    private ToastQueue finishedQueue;
    private int counter=0;

    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t=finishedQueue.take();
                if(t.getId()!=counter++ || t.getStatus()!=Toast.Status.JAMMD){
                    System.out.println(">>>> Error:"+t);
                    System.exit(1);
                }else {
                    System.out.println("Chomp"+t);
                }
            }
        }catch (Exception e){
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}


public class ToastMatic{

    public static void main(String[] args) throws Exception{
        ToastQueue dryQueue=new ToastQueue(),
                   butterQueue=new ToastQueue(),
                   finishedQueue=new ToastQueue();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butter(dryQueue,butterQueue));
        exec.execute(new Jammer(butterQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();

    }


}
