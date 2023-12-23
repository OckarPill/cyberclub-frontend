package ru.ockarpill.cyberclub.model;

public record Tariff (
        int id,
        String name,
        int hours,
        int cost
){
}
