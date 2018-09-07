package IgniteExamples.ignitemessaging;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteMessaging;
import org.apache.ignite.Ignition;

public class IgniteNode1 {

    public static void main( String[] args )
    {

        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfiguration());

        IgniteMessaging rmtMsg = ignite.message(ignite.cluster().forLocal());

        rmtMsg.remoteListen("MyTopic", (nodeId, msg) -> {
            System.out.println("Received ordered message [msg=" + msg + ", from=" + nodeId + ']');
            return true;
        } );

     }
}
