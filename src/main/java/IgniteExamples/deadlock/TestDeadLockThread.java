package IgniteExamples.deadlock;

import org.apache.ignite.lang.IgniteRunnable;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TestDeadLockThread implements IgniteRunnable {


    ExecutorService service;

    public TestDeadLockThread(ExecutorService service){
        this.service = service;
    }


    @Override
    public void run() {
            System.out.println("Parent Thread::" + Thread.currentThread());
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Future<Integer> f= service.submit(new ChildThread());
        try {
            System.out.println(f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


 class ChildThread implements Callable<Integer> {

     @Override
     public Integer call() throws Exception {
         int sum = 0;
         for (int i =0;i < 100 ; i++){
             try {
                 System.out.println("Child ::"+ Thread.currentThread() + "::" +  i);
                 Thread.sleep(10L);
                 sum = sum + i;

             } catch (InterruptedException e) {e.printStackTrace();
             }
         }
         return sum;
     }
 }
