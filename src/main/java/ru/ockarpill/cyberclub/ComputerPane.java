//package ru.ockarpill.cyberclub;
//
//import ru.ockarpill.cyberclub.model.Computer;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ComputerPane extends JPanel {
//    public ComputerPane(Computer computer) {
//        setLayout(new GridBagLayout());
//
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx = 0;
//        constraints.weightx = 0;
//        constraints.fill = GridBagConstraints.NONE;
//        constraints.anchor = GridBagConstraints.WEST;
//
//        add(new JLabel("Computer"), constraints);
//        add(new JLabel("ID: " + computer.id()), constraints);
//        add(new JLabel("#: " + computer.number()), constraints);
//
//        if (computer.booking() != null) {
//            add(new JLabel("Booked"), constraints);
//        }
//        if (computer.session() != null) {
//            add(new JLabel("Occupied"), constraints);
//        }
//
//        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
//    }
//}
