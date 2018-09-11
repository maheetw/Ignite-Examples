package IgniteExamples.ignitequeries;

import IgniteExamples.CustomIgniteConfiguration;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.internal.marshaller.optimized.OptimizedMarshaller;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class QueryExample {

    public static void main(String []s){
        CacheConfiguration<Integer, String> cacheConfiguration = new CacheConfiguration<>();
        cacheConfiguration.setName("MYCACHE");
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setTableName("Test");
        queryEntity.setKeyType(Integer.class.getName());
        queryEntity.setValueType(String.class.getName());
        cacheConfiguration.setQueryEntities(Arrays.asList(queryEntity));
        cacheConfiguration.setIndexedTypes(Integer.class, String.class);
        IgniteConfiguration configuration = new CustomIgniteConfiguration().getConfiguration();
        configuration.setCacheConfiguration(cacheConfiguration);
        Ignite ignite = Ignition.start(configuration);

        // Placing data into cache
        IgniteCache<Integer, String> cache = ignite.getOrCreateCache("MYCACHE");

        for (int i =0 ;i < 100 ;i ++){
            cache.put(i, "saurabh" + i);
        }

        SqlFieldsQuery top10Qry = new SqlFieldsQuery(
                "select _key, _val from  Test limit 10");

        List<List<?>> top10 = cache.query(top10Qry).getAll();
        System.out.println(top10.size());
        System.out.println(top10);
    }

}
