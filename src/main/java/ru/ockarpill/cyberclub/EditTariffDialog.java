package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Tariff;
import ru.ockarpill.cyberclub.model.TariffRequest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;


public class EditTariffDialog extends JDialog {
    public EditTariffDialog(
            JFrame owner,
            Consumer<TariffRequest> callback,
            Tariff tariff
    ) {
        super(owner, tariff == null ? "Add new tariff" : "Edit tariff", ModalityType.APPLICATION_MODAL);

        JPanel contents = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        contents.setLayout(layout);

        JLabel idtext = new JLabel("ID:");
        JLabel nametext = new JLabel("Name:");
        JLabel hourstext = new JLabel("Hours:");
        JLabel costtext = new JLabel("Cost:");
        JTextField fieldId = new JTextField();
        JTextField fieldName = new JTextField();
        JTextField fieldHours = new JTextField();
        JTextField fieldCost = new JTextField();
        JButton button = new JButton("Save");

        if (tariff == null) {
            idtext.setVisible(false);
            fieldId.setVisible(false);
        } else {
            idtext.setEnabled(false);
            fieldId.setEnabled(false);
        }
        contents.setBorder(new EmptyBorder(16, 16, 16, 16));

        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        contents.add(idtext, constraints);
        contents.add(fieldId, constraints);
        contents.add(nametext, constraints);
        contents.add(fieldName, constraints);
        contents.add(hourstext, constraints);
        contents.add(fieldHours, constraints);
        contents.add(costtext, constraints);
        contents.add(fieldCost, constraints);

        constraints.insets = new Insets(16, 0, 0, 0);
        contents.add(button, constraints);

        setContentPane(contents);
        pack();
        setMinimumSize(new Dimension(200, 0));
        setResizable(false);

        setLocationRelativeTo(null);

        if (tariff != null) {
            fieldId.setText(String.valueOf(tariff.id()));
            fieldName.setText(tariff.name());
            fieldHours.setText(String.valueOf(tariff.hours()));
            fieldCost.setText(String.valueOf(tariff.cost()));
        }

        button.addActionListener(e -> {
            String name;
            int hours, cost;
            try {
                name = fieldName.getText();
                hours = Integer.parseInt(fieldHours.getText());
                cost = Integer.parseInt(fieldCost.getText());
                TariffRequest request = new TariffRequest(name, hours, cost);
                callback.accept(request);
                this.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(new JLabel(), "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


    }
}
