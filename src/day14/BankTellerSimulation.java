package day14;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 银行出纳仿真
 */

class Customer{
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "serviceTime=" + serviceTime +
                '}';
    }
}

//Teach the customer line to display itself
class CustomerLine extends ArrayBlockingQueue<Customer>{

    public CustomerLine(int maxLineSize){
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if (this.size()==0){
            return "[Empty]";
        }
        StringBuilder result=new StringBuilder();
        for (Customer customer:this){
            result.append(customer);
        }
        return result.toString();
    }
}

//添加随机数量的客户到队列里面
class CustomerGenerator implements Runnable{

    private CustomerLine customers;
    private static Random random=new Random(47);
    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        }catch (Exception e){
             System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

class Teller implements Runnable,Comparable<Teller>{

    private static int counter=0;

    private final int id=counter++;

    private int customersServed=0;

    private CustomerLine customers;

    private boolean servingCustomerLine=true;

    public Teller(CustomerLine cq){
        customers=cq;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
              Customer customer=customers.take();
              TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
              synchronized (this){
                  customersServed++;
                  while (!servingCustomerLine){
                      wait();
                  }
              }
            }

        }catch (InterruptedException e){
            System.out.println(this+"interrupted");
        }
        System.out.println(this+"terminating");
    }
    public synchronized void doSometingElse(){
        customersServed=0;
        servingCustomerLine=false;
    }

    public synchronized void serveCustomerLine(){
       assert !servingCustomerLine:"already serving: "+this;
       servingCustomerLine=true;
       notifyAll();
    }

    @Override
    public String toString() { return "Teller{" + "id=" + id + '}'; }
    public String shortString(){return "T"+id;}
    //Use by priority queue

    @Override
    public synchronized  int compareTo(Teller o) {
        return customersServed<o.customersServed?-1:(customersServed==o.customersServed?0:1);
    }
}

   class TellerManager implements Runnable{

    private ExecutorService exec;

    private CustomerLine customers;

    private PriorityQueue<Teller> workingTellers=new PriorityQueue<>();

    private Queue<Teller> tellersDoingOtherThings=new LinkedList<>();

    private int adjustmentPeriod;

    private static Random rand=new Random(47);

       public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
           this.exec = exec;
           this.customers = customers;
           this.adjustmentPeriod = adjustmentPeriod;
           Teller teller=new Teller(customers);
           exec.execute(teller);
           workingTellers.add(teller);
       }

       public void adjustTellerNumber(){
           //this is actually a control system By adjusting
           //the numbers,you can reveal stability issues in
           //the control mechaism
           //if line is too long,add another teller
           if (customers.size()/workingTellers.size()>2){
               //if tellers are on break or doing
               //another job,bring one back
               if (tellersDoingOtherThings.size()>0){
                   Teller teller=tellersDoingOtherThings.remove();
                   teller.serveCustomerLine();
                   workingTellers.offer(teller);
                   return;
               }
               Teller teller=new Teller(customers);
               exec.execute(teller);
               workingTellers.add(teller);
               return;
           }
           //if line is short enough remove a teller;
           if (workingTellers.size()>1&&customers.size()/workingTellers.size()<2){
               reassignOneTeller();
           }
           if (customers.size()==0){
               while (workingTellers.size()>1){
                   reassignOneTeller();
               }

           }
       }

       private void reassignOneTeller(){
           Teller teller=workingTellers.poll();
           teller.doSometingElse();
           tellersDoingOtherThings.offer(teller);
       }

       @Override
       public void run() {
           try{
               while (!Thread.interrupted()){
                   TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                   adjustTellerNumber();
                   System.out.print(customers+"{");
                   for (Teller teller:workingTellers){

                       System.out.print(teller.shortString()+" ");
                   }
                   System.out.println("}");
               }

           }catch (InterruptedException e){
               System.out.println(this+"interrupted");

           }
           System.out.println(this+"terminating");
       }


       @Override
       public String toString() {
           return "TellerManager";
       }
   }

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE=50;
    static final int ADJUSTMENT_PERIOD=1000;
    public static void main(String[] args) throws Exception{

        ExecutorService exec= Executors.newCachedThreadPool();
        //如果队伍太长客户将会离开
        CustomerLine customers=new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec,customers,ADJUSTMENT_PERIOD));
        if (args.length>0){
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        }else {
            System.out.println("press enter to quit");
            System.in.read();
        }
        exec.shutdownNow();

    }



}
