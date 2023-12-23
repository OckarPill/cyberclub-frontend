package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.User;
import ru.ockarpill.cyberclub.model.UserRequest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.function.Consumer;

public class EditUserDialog extends JDialog {
    public EditUserDialog(
            JFrame owner,
            Consumer<UserRequest> callback,
            User user
    ) {
        super(owner, user == null ? "Add new user" : "Edit user", ModalityType.APPLICATION_MODAL);

        JPanel contents = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        contents.setLayout(layout);

        JLabel idtext = new JLabel("ID:");
        JLabel nametext = new JLabel("Name:");
        JLabel agetext = new JLabel("Age:");
        JTextField fieldId = new JTextField();
        JTextField fieldName = new JTextField();
        JTextField fieldAge = new JTextField();
        JButton button = new JButton("Save");

        if (user == null) {
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
        contents.add(agetext, constraints);
        contents.add(fieldAge, constraints);

        constraints.insets = new Insets(16, 0, 0, 0);
        contents.add(button, constraints);

        setContentPane(contents);
        pack();
        setMinimumSize(new Dimension(200, 0));
        setResizable(false);

        setLocationRelativeTo(null);

        if (user != null) {
            fieldId.setText(String.valueOf(user.id()));
            fieldName.setText(user.name());
            fieldAge.setText(String.valueOf(user.age()));
        }

        button.addActionListener(e -> {
            int age;
            String name;
            try {
                name = fieldName.getText();
                age = Integer.parseInt(fieldAge.getText());
                UserRequest request = new UserRequest(name, age);
                callback.accept(request);
                this.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(new JLabel(), "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


    }
}
