package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

public record BookingRequest(
        int computer_id,
        int customer_id,
        ZonedDateTime start,
        ZonedDateTime end
) {
}
