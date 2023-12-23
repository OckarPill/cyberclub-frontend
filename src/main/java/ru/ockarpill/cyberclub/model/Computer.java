package ru.ockarpill.cyberclub.model;

public record Computer (
        Integer id,
        int number,
        ComputerBooking booking,
        ComputerSession session
){}
