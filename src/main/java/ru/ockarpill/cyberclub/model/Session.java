package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

public record Session(
        int id,
        int computer_id,
        int customer_id,
        int tariff_id,
        ZonedDateTime start,
        ZonedDateTime end
) {
}
