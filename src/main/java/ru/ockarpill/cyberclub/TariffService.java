package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Tariff;
import ru.ockarpill.cyberclub.model.TariffRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class TariffService {
    public CompletableFuture<List<Tariff>> getTariffs() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of();
        });
    }

    public CompletableFuture<Tariff> addTariff(TariffRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Tariff(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), input.name(), input.hours(),input.cost());
        });
    }

    public CompletableFuture<Tariff> editTariff(int id, TariffRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Tariff(id, input.name(), input.hours(),input.cost());
        });
    }
}
