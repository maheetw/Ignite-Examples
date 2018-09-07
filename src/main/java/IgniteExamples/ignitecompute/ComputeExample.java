package IgniteExamples.ignitecompute;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.lang.IgniteRunnable;

public class ComputeExample {

    public static void main(String[] args) {
        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfiguration());
        ComputeExample exampleRunner = new ComputeExample();
        exampleRunner.runExample1(ignite);
    }

    void runExample1(Ignite ignite){
        ignite.compute().broadcast(new IgniteRunnable() {
            @Override
            public void run() {
                System.out.println("Hello Node!");
            }
        });
    }


    void runExample2(Ignite ignite){
        ignite.compute().runAsync(new IgniteRunnable() {
            @Override
            public void run() {
                System.out.println("Hello Node!");
            }
        });
    }



}