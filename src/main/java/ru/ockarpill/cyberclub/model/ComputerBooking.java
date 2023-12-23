package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

public record ComputerBooking (
        ZonedDateTime start,
        ZonedDateTime end,
        int userId
) {
}
