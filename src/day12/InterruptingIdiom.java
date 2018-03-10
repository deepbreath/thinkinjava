package day12;




class NeedsCleanup{

    private final int id;

   NeedsCleanup(int id) {
        this.id = id;
        System.out.print("NeedsCleanup "+id);
    }
    public void cleanup(){
        System.out.print("Cleaning up "+id);
    }

}

class Blocked3 implements Runnable{
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                //point1
                NeedsCleanup n1=new NeedsCleanup(1);



            }

        }catch (Exception e){

        }

    }
}

public class InterruptingIdiom {

}
