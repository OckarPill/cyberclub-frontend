package ru.ockarpill.cyberclub.model;

public record TariffRequest(
        String name,
        int hours,
        int cost
) {
}
