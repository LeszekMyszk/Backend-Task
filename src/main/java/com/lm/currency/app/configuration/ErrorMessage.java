package com.lm.currency.app.configuration;

import java.time.LocalDateTime;

public record ErrorMessage(LocalDateTime timestamp, String exception) {
}