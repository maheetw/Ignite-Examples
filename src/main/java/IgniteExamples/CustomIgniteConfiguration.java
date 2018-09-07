package IgniteExamples;

import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ExecutorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.NullLogger;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomIgniteConfiguration {


    public IgniteConfiguration getConfiguration(){

        TcpDiscoveryMulticastIpFinder finder  = new TcpDiscoveryMulticastIpFinder();
        List<String> address = new ArrayList();
        for (int i=47500 ;i<=47509 ;i++){
            address.add("127.0.0.1:"+ i);
        }
        finder.setAddresses(address);
        TcpDiscoverySpi tcpDiscoverySpi = new TcpDiscoverySpi();
        tcpDiscoverySpi.setIpFinder(finder);
        IgniteConfiguration config = new IgniteConfiguration();
        Map<String, String> userattributes = new HashMap<>();
        userattributes.put("clusterGroup", "ignite-test-1");
        userattributes.put("ROLE", "worker");
        config.setUserAttributes(userattributes);
        config.setDiscoverySpi(tcpDiscoverySpi);
        config.setGridLogger(new NullLogger());
        return config;
    }

    public IgniteConfiguration getConfigurationWithCache(){
        IgniteConfiguration configuration = getConfiguration();
        CacheConfiguration<Integer, String> cacheConfiguration = new CacheConfiguration<>();
        cacheConfiguration.setName("default");
        cacheConfiguration.setAtomicityMode(CacheAtomicityMode.ATOMIC);
        cacheConfiguration.setBackups(1);
        configuration.setCacheConfiguration(cacheConfiguration);
        return configuration;
    }

}