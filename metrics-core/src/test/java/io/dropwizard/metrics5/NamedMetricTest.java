package io.dropwizard.metrics5;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NamedMetricTest {
    private MetricRegistry registry;
    
    @Before
    public void setup() {
        registry = new MetricRegistry();
    }

    @Test
    public void prometheusStyleTags() {
        /*
        class YourClass {
          static final Counter requests = Counter.build()
             .name("my_library_requests_total").help("Total requests.")
             .labelNames("method").register();

          void processGetRequest() 
            requests.labels("get").inc();
            // Your code here.
          }
        }
        */
        NamedMetric<Counter> requests = registry.counter2()
                .resolve("my_library_requests_total");
                
        // void processGetRequest() 
        requests.tagged("method", "get").get().inc();
        
        
        assertThat(registry.counter(MetricName.empty()
                .resolve("my_library_requests_total")
                .tagged("method", "get"))
                    .getCount()).isEqualTo(1);
    }
}
