package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.User;
import ru.ockarpill.cyberclub.model.UserRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class UserService {
    public CompletableFuture<List<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return List.of();
        });
    }

    public CompletableFuture<User> addUser(UserRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new User(ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE), input.name(), input.age());
        });
    }

    public CompletableFuture<User> editUser(int id, UserRequest input) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return new User(id, input.name(), input.age());
        });
    }
}
