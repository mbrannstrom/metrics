package io.dropwizard.metrics5;

import java.util.function.Function;
import java.util.function.Supplier;

public class NamedMetric<T extends Metric> {
    private final MetricName name;
    private final Function<MetricName,T> namedSupplier;
    NamedMetric(MetricName name, Function<MetricName,T> namedSupplier) {
        this.name = name;
        this.namedSupplier = namedSupplier;
    }
    NamedMetric<T> resolve(String... parts) {
        return new NamedMetric<>(name.resolve(parts), namedSupplier);
    }
    NamedMetric<T> tagged(String... pairs) {
        return new NamedMetric<>(name.tagged(pairs), namedSupplier);
    }
    
    T get() {
        return namedSupplier.apply(name);
    }
}
