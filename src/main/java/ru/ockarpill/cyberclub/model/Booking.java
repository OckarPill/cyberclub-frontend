package ru.ockarpill.cyberclub.model;

import java.time.ZonedDateTime;

        public record Booking(
                int id,
                int computer_id,
                int customer_id,
                ZonedDateTime start,
                ZonedDateTime end
) {
}
