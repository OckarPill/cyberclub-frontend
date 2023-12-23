package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

public record ComputerSession (
        ZonedDateTime start,
        ZonedDateTime end,
        int userId
) {
}

