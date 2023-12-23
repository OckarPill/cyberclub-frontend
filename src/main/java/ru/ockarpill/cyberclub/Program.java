package ru.ockarpill.cyberclub;

import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;

public class Program {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        FlatIntelliJLaf.setup();

        MainWindow mainWindow = new MainWindow("OckarPill");


        mainWindow.setSize(600, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(true);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }
}