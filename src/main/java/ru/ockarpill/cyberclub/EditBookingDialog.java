package ru.ockarpill.cyberclub;

import com.github.lgooddatepicker.components.DateTimePicker;
import ru.ockarpill.cyberclub.model.Booking;
import ru.ockarpill.cyberclub.model.BookingRequest;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.function.Consumer;


public class EditBookingDialog extends JDialog {
    public EditBookingDialog(
            JFrame owner,
            Consumer<BookingRequest> callback,
            Booking booking
    ) {
        super(owner, booking == null ? "Add new booking" : "Edit booking", ModalityType.APPLICATION_MODAL);

        JPanel contents = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        contents.setLayout(layout);

        JLabel idtext = new JLabel("ID:");
        JLabel computer_idtext = new JLabel("Computer ID:");
        JLabel customer_idtext = new JLabel("Customer ID:");
        JLabel datetext = new JLabel("Session start:");
        JTextField fieldId = new JTextField();
        JTextField fieldComputer_Id = new JTextField();
        JTextField fieldCustomer_Id = new JTextField();
        DateTimePicker fieldDate = new DateTimePicker();

        JButton button = new JButton("Save");

        if (booking == null) {
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
        contents.add(computer_idtext, constraints);
        contents.add(fieldComputer_Id, constraints);
        contents.add(customer_idtext, constraints);
        contents.add(fieldCustomer_Id, constraints);
        contents.add(datetext,constraints);
        contents.add(fieldDate,constraints);

        constraints.insets = new Insets(16, 0, 0, 0);
        contents.add(button, constraints);

        setContentPane(contents);
        pack();
        setMinimumSize(new Dimension(200, 0));
        setResizable(false);

        setLocationRelativeTo(null);

        if (booking != null) {
            fieldId.setText(String.valueOf(booking.id()));
            fieldComputer_Id.setText(String.valueOf(booking.computer_id()));
            fieldCustomer_Id.setText(String.valueOf(booking.customer_id()));

        }

        button.addActionListener(e -> {
            int computer_id, customer_id;
            ZonedDateTime start, end;
            try {
                computer_id = Integer.parseInt(fieldComputer_Id.getText());
                customer_id = Integer.parseInt(fieldCustomer_Id.getText());
                start= ZonedDateTime.now().with(ChronoField.MILLI_OF_SECOND,0);
                end = ZonedDateTime.now().with(ChronoField.MILLI_OF_SECOND,0);
                BookingRequest request = new BookingRequest(computer_id,customer_id,start,end);
                callback.accept(request);
                this.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(new JLabel(), "Invalid input. Please enter valid data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
