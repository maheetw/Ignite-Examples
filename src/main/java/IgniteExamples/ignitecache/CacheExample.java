package IgniteExamples.ignitecache;


import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.core.env.SystemEnvironmentPropertySource;

public class CacheExample{

    public static void main( String[] args )
    {
        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfigurationWithCache());

        // Placing data into cache
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("default");
        for (int i =0 ;i < 10 ;i ++){
            cache.put(i, "saurabh" + i);
        }

        //sending messages to the nodes which have that element stored
        for (int i =0;i< 10 ;i ++) {
            ClusterNode node = ignite.affinity("default").mapKeyToNode(i);
            System.out.println(node);
            ClusterGroup group = ignite.cluster().forNode(node);
            ignite.message(group).send("MyTopic", Integer.toString(i));
        }
    }
}
