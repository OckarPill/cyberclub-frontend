package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Session;
import ru.ockarpill.cyberclub.model.SessionRequest;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class SessionService {
    public CompletableFuture<List<Session>> getSessions() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of();
        });
    }

    public CompletableFuture<Session> addSession(SessionRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Session(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), input.computer_id(), input.customer_id(),input.tariff_id(), ZonedDateTime.now(), ZonedDateTime.now());
        });
    }

    public CompletableFuture<Session> editSession(int id, SessionRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Session(id,input.computer_id(), input.customer_id(),input.tariff_id(), ZonedDateTime.now(), ZonedDateTime.now());
        });
    }
}
