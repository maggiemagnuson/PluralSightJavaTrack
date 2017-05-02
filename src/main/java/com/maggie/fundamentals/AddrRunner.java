package com.maggie.fundamentals;

import java.util.concurrent.*;

/**
 * Created by z001hqv on 2/17/17.
 */
public class AddrRunner {

    public static void main(String[] args) {

        String[] inFiles = {"./data/random_integers_1.txt",
                "./data/random_integers_2.txt",
                "./data/random_integers_3.txt",
                "./data/random_integers_4.txt",
                "./data/random_integers_5.txt",
                "./data/random_integers_6.txt"};

        String[] outFiles = {"./data/random_integers_1.out.txt",
                "./data/random_integers_2.out.txt",
                "./data/random_integers_3.out.txt",
                "./data/random_integers_4.out.txt",
                "./data/random_integers_5.out.txt",
                "./data/random_integers_6.out.txt"};

        //AddrRunner.runManagedThreads(inFiles, outFiles);
        //AddrRunner.runExecutorService(inFiles, outFiles);
        AddrRunner.runCallable(inFiles, outFiles);
    }

    public static void runExecutorService(String[] inFiles, String[] outFiles){

        ExecutorService es = Executors.newFixedThreadPool(3);

        for (int i = 0; i < inFiles.length; i++){
            Addr addr = new Addr(inFiles[i], outFiles[i]);
            es.submit((Runnable)addr);
        }
        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void runManagedThreads(String[] inFiles, String[] outFiles){
        //Gives us the ability to store references to multiple threads
        Thread[] threads = new Thread[inFiles.length];

        for (int i = 0; i < inFiles.length; i++) {
            Addr addr = new Addr(inFiles[i], outFiles[i]);
            threads[i] = new Thread(addr);
            threads[i].start();
        }

        //Ensure main thread will continue running until all threads are complete
        try {
            for (Thread thread : threads) {
                thread.join(); //Blocks waiting for thread completion
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void runCallable(String[] inFiles, String[] outFiles){
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<Integer>[] results = new Future[inFiles.length];

        for (int i = 0; i < inFiles.length; i++){
            Addr addr = new Addr(inFiles[i], outFiles[i]);
            results[i] = es.submit((Callable)addr);
        }

        for (Future<Integer> result : results){
            try {
                System.out.println(result.get());
            } catch (ExecutionException e){
                //Get the original exception
                Throwable addrEx = e.getCause();
                addrEx.printStackTrace();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
