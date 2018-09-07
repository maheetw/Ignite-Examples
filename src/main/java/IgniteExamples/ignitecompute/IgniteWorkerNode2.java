package IgniteExamples.ignitecompute;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class IgniteWorkerNode2 {
    public static void main( String[] args ) {
        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfiguration());


    }
}
