package IgniteExamples.ignitecontiniousqueries;


import org.apache.ignite.cache.query.ContinuousQuery;
import org.apache.ignite.cache.query.ScanQuery;
import javax.cache.configuration.Factory;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryEventFilter;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ContinousQuerySetup implements Serializable {

    public ContinuousQuery<Integer, String> setUp(BiConsumer<Integer,String> consumer, Function<Integer, Boolean> conditionFunction){
        ContinuousQuery<Integer, String> qry = new ContinuousQuery<>();

        // Initial Query from where you need to start reading from cache
        qry.setInitialQuery(new ScanQuery<Integer, String>((k, v) -> k > 0));

        qry.setLocalListener(
                (evts) ->  evts.forEach(e -> consumer.accept(e.getKey(), e.getValue()))
                );

        qry.setRemoteFilterFactory(new Factory<CacheEntryEventFilter<Integer, String>>() {
            @Override
            public CacheEntryEventFilter<Integer, String> create() {
                return new CacheEntryEventFilter<Integer, String>() {
                    @Override public boolean evaluate(CacheEntryEvent<? extends Integer, ? extends String> e) {
                        return conditionFunction.apply(e.getKey());
                    }
                };
            }
        });

        return qry;
    }
}
