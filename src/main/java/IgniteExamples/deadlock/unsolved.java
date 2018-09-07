package IgniteExamples.deadlock;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.concurrent.ExecutorService;

public class unsolved {

    public static void main( String[] args )
    {
        IgniteConfiguration configuration = new CustomIgniteConfiguration().getConfiguration();
        configuration.setPublicThreadPoolSize(2);
        Ignite ignite = Ignition.start(configuration);
        ExecutorService service = ignite.executorService();
        ignite.compute().runAsync(new TestDeadLockThread( service));
        ignite.compute().runAsync(new TestDeadLockThread( service));



    }
}
