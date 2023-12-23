package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Booking;
import ru.ockarpill.cyberclub.model.BookingRequest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class BookingService {
    public CompletableFuture<List<Booking>> getBookings() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of();
        });
    }

    public CompletableFuture<Booking> addBooking(BookingRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Booking(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), input.computer_id(), input.customer_id(), ZonedDateTime.now(), ZonedDateTime.now());
        });
    }

    public CompletableFuture<Booking> editBooking(int id, BookingRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Booking(id,input.computer_id(), input.customer_id(), ZonedDateTime.now(), ZonedDateTime.now());
        });
    }
}
