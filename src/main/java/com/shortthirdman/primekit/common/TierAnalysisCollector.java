package com.shortthirdman.primekit.common;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Custom collector to analyze orders by customer tier, showing average order value and total items.
 * @author ShortThirdMan
 * @since 1.1.1
 */
public class TierAnalysisCollector implements Collector<Order, TierAnalysis, TierAnalysis> {

    @Override
    public Supplier<TierAnalysis> supplier() {
        return () -> new TierAnalysis(0, BigDecimal.ZERO, 0);
    }

    @Override
    public BiConsumer<TierAnalysis, Order> accumulator() {
        return (analysis, order) -> {
            BigDecimal orderTotal = order.items().stream()
                    .map(item -> item.product().price()
                            .multiply(BigDecimal.valueOf(item.quantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            long itemCount = order.items().stream()
                    .mapToLong(OrderItem::quantity)
                    .sum();

            analysis = new TierAnalysis(
                    analysis.orderCount() + 1,
                    analysis.totalRevenue().add(orderTotal),
                    analysis.totalItems() + itemCount
            );
        };
    }

    @Override
    public BinaryOperator<TierAnalysis> combiner() {
        return TierAnalysis::combine;
    }

    @Override
    public Function<TierAnalysis, TierAnalysis> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH
        ));
    }
}
