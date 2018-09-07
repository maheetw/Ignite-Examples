package IgniteExamples.ignitemessaging;

        import IgniteExamples.CustomIgniteConfiguration;
        import org.apache.ignite.Ignite;
        import org.apache.ignite.IgniteMessaging;
        import org.apache.ignite.Ignition;

public class App
{
    public static void main( String[] args )
    {

        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfiguration());
        IgniteMessaging rmtMsg = ignite.message(ignite.cluster().forRemotes());

        for (int i = 0; i < 10; i++)
            rmtMsg.sendOrdered("MyTopic", Integer.toString(i), -1L);
    }
}
