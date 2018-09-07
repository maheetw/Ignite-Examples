package IgniteExamples.deadlock;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.ExecutorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

import java.util.concurrent.ExecutorService;

public class solved {
    public static void main( String[] args ) {
        IgniteConfiguration configuration = new CustomIgniteConfiguration().getConfiguration();

        configuration.setExecutorConfiguration(new ExecutorConfiguration("myPool").setSize(2));
        configuration.setPublicThreadPoolSize(2);
        Ignite ignite = Ignition.start(configuration);
        ExecutorService service = ignite.executorService();
        ignite.compute().withExecutor("myPool").runAsync(new TestDeadLockThread( service));
        ignite.compute().withExecutor("myPool").runAsync(new TestDeadLockThread( service));
    }
}
