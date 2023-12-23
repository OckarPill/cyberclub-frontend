package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Computer;
import ru.ockarpill.cyberclub.model.ComputerRequest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;


public class EditComputerDialog extends JDialog {
    public EditComputerDialog(
            JFrame owner,
            Consumer<ComputerRequest> callback,
            Computer computer
    ) {
        super(owner, computer == null ? "Add new computer" : "Edit computer", ModalityType.APPLICATION_MODAL);

        JPanel contents = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        contents.setLayout(layout);

        JLabel idtext = new JLabel("ID:");
        JLabel numbertext = new JLabel("Number:");
        JTextField fieldId = new JTextField();
        JTextField fieldNumber = new JTextField();
        JButton button = new JButton("Save");

        if (computer == null) {
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
        contents.add(numbertext, constraints);
        contents.add(fieldNumber, constraints);

        constraints.insets = new Insets(16, 0, 0, 0);
        contents.add(button, constraints);

        setContentPane(contents);
        pack();
        setMinimumSize(new Dimension(200, 0));
        setResizable(false);

        setLocationRelativeTo(null);

        if (computer != null) {
            fieldId.setText(String.valueOf(computer.id()));
            fieldNumber.setText(String.valueOf(computer.number()));
        }

        button.addActionListener(e -> {
            int number;
            try {
                number = Integer.parseInt(fieldNumber.getText());
                ComputerRequest request = new ComputerRequest(number);
                callback.accept(request);
                this.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(new JLabel(), "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


    }
}
