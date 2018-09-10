package IgniteExamples.ignitelifecycle;


/*
* Lifecycle Event Types
*
* BEFORE_NODE_START: Invoked before Ignite node startup routine is initiated
* AFTER_NODE_START:  Invoked right after Ignite node has started.
* BEFORE_NODE_STOP:  Invoked right before Ignite stop routine is initiated.
* AFTER_NODE_STOP: Invoked right after Ignite node has stopped.
*
* */

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;

import java.time.LocalDateTime;

public class LifeCycleExample {

    public static void main(String []s){
        IgniteConfiguration configuration = new CustomIgniteConfiguration().getConfiguration();
        configuration.setLifecycleBeans(new MyIgniteLifeCycle());
        Ignite ignite = Ignition.start(configuration);
        Ignition.stop(true);

    }
}


class MyIgniteLifeCycle implements LifecycleBean{

    @Override
    public void onLifecycleEvent(LifecycleEventType lifecycleEventType) throws IgniteException {
        if (LifecycleEventType.BEFORE_NODE_START == lifecycleEventType){
            System.out.println( "Invoked before Ignite node startup routine is initiated." + LocalDateTime.now());
        }
        if (LifecycleEventType.AFTER_NODE_START == lifecycleEventType){
            System.out.println("Invoked right after Ignite node has started."+ LocalDateTime.now());
        }
        if (LifecycleEventType.BEFORE_NODE_STOP == lifecycleEventType){
            System.out.println("Invoked right before Ignite stop routine is initiated."+ LocalDateTime.now());
        }
        if (LifecycleEventType.AFTER_NODE_STOP == lifecycleEventType){
            System.out.println("Invoked right after Ignite node has stopped."+ LocalDateTime.now());
        }
    }
}
