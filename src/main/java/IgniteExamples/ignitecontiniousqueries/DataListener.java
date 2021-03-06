package IgniteExamples.ignitecontiniousqueries;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.ContinuousQuery;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class DataListener {

    public static void main(String[] args) {
        Ignite ignite = Ignition.start(new CustomIgniteConfiguration().getConfigurationWithCache());
        IgniteCache<Integer, String> cache = ignite.cache("default");
        for (int i =0 ;i < 10 ;i ++){
            cache.put(i, "saurabh" + i);
        }

        BiConsumer<Integer, String> consumer = (key, value) -> {
            System.out.println(key + ":" + value);
        };

        Function<Integer, Boolean>  condition = (Function<Integer, Boolean> & Serializable)((k) -> {return k%5 == 0;});

        ContinuousQuery qry = new ContinousQuerySetup().setUp(consumer, condition);

        cache.query(qry);

        cache.put(21, "hello");
        cache.put(24, "hi");
        cache.put(25, "sure");

    }
}