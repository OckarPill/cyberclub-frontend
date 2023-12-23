package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Computer;
import ru.ockarpill.cyberclub.model.ComputerRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerService {
    public CompletableFuture<List<Computer>> getComputers() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of();
        });
    }

    public CompletableFuture<Computer> addComputer(ComputerRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Computer(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), input.number(), null, null);
        });
    }

    public CompletableFuture<Computer> editComputer(int id, ComputerRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new Computer(id, input.number(), null, null);
        });
    }
}
