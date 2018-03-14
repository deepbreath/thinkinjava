package day16;


import day6.Generated;
import day6.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 专门用于任何类型的容器上执行测试,包括各种Map在内,其中泛型参数C表示容器类型
 *
 */
public abstract class Tester<C> {
    static int testReps=10;
    static int testCycles=1000;
    static int countainerSize=1000;
    abstract C containerInitializer();
    abstract void startReaderAndWriters();
    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult=0;
    volatile long readTime=0;
    volatile long writeTime=0;
    CountDownLatch endLatch;
    static ExecutorService exec= Executors.newCachedThreadPool();
    Integer[] writeData;

    public Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId+" "+nReaders+"r "+nWriters+"w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData= Generated.array(Integer.class,new RandomGenerator.Integer(),countainerSize);
        for (int i=0;i<testReps;i++){
            runTest();
            readTime=0;
            writeTime=0;
        }
    }

    void runTest(){
        endLatch=new CountDownLatch(nReaders+nWriters);
        testContainer=containerInitializer();
        startReaderAndWriters();
        try{
            endLatch.await();

        }catch (InterruptedException e){
              System.out.println("endLatch interruptedException");
        }
        System.out.printf("%-27s %14d %14d\n",testId,readTime,writeTime);

        if (readTime!=0&&writeTime!=0){
            System.out.printf("%-27s %14d\n","readTime+writeTime=",readTime+writeTime);
        }
    }

    abstract class TestTask implements Runnable{
        abstract void test();
        abstract void putResults();
        long duration;

        @Override
        public void run() {
            long startTime=System.nanoTime();
            test();
            duration=System.nanoTime()-startTime;
            synchronized (Tester.this){
                putResults();
            }
            endLatch.countDown();
        }

    }

    public static void initMain(String[] args){
        if (args.length > 0) {
            testReps=new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles=new Integer(args[1]);
        }
        if (args.length > 2) {
            countainerSize=new Integer(args[2]);
        }
        System.out.printf("%-27s %14d %14d\n","Type","readTime","writeTime");
    }


}
