package ru.ockarpill.cyberclub;

import com.formdev.flatlaf.ui.FlatTabbedPaneUI;

import javax.swing.*;

public class MainWindow extends JFrame { //объявляем кнопки

    public MainWindow(String winTitle) {
        super(winTitle);

        JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT);
        tabPane.setUI(new CustomTabbedPaneUI());

        tabPane.addTab("Users", new UsersPane(this));
        tabPane.addTab("Computers", new ComputersPane(this));
//        tabPane.addTab("Balances", new UsersPane(this));
        tabPane.addTab("Bookings", new BookingPane(this));
//        tabPane.addTab("Payments", new UsersPane(this));
        tabPane.addTab("Tariffs", new TariffsPane(this));
        tabPane.addTab("Sessions", new SessionPane(this));


        setContentPane(tabPane);
    }

    private static class CustomTabbedPaneUI extends FlatTabbedPaneUI {
        @Override
        protected void installDefaults() {
            super.installDefaults();
            showContentSeparator = false;
            tabAreaInsets.left += 38;
            minimumTabWidth = 150;
        }
    }
}
