package com.lm.currency.app.configuration;

import java.time.LocalDateTime;
import java.util.function.Supplier;

@FunctionalInterface
public interface TimeService extends Supplier<LocalDateTime> {
    LocalDateTime get();
}