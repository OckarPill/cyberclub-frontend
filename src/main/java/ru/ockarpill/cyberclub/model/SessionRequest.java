package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

public record SessionRequest(
        int computer_id,
        int customer_id,
        int tariff_id,
        ZonedDateTime start,
        ZonedDateTime end
) {
}
