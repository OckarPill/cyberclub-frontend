package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Booking;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookingPane extends JPanel {
    private final JFrame parent;
    private final JButton dialogbutton = new JButton("Add booking");
    private final JButton edit = new JButton("Edit");
    private final JButton delete = new JButton("Delete");
    private final BookingService bookingService = new BookingService();

    public BookingPane(JFrame parent) {
        super(new GridBagLayout());
        this.parent = parent;
        List<Booking> bookings = new ArrayList<>();

        edit.setEnabled(false);
        delete.setEnabled(false);

        BookingTableModel tableModel = new BookingTableModel();

        bookingService.getBookings().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                bookings.addAll(result);
                tableModel.setBookings(bookings);
            });
        });

        JTable table = new JTable(tableModel);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting())
                return;
            edit.setEnabled(selectionModel.getSelectedItemsCount() != 0);
            delete.setEnabled(selectionModel.getSelectedItemsCount() != 0);
        });

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.weightx = 1;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST;

        JPanel tools = new JPanel(new FlowLayout());

        tools.add(dialogbutton);
        tools.add(edit);
        tools.add(delete);

        this.add(tools, constraints);

        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(table), constraints);

        dialogbutton.addActionListener(e -> {
            EditBookingDialog addBookingForm = new EditBookingDialog(parent, request -> {
                bookingService.addBooking(request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        bookings.add(result);
                        tableModel.setBookings(bookings);
                    });
                });
            }, null);
            addBookingForm.setVisible(true);
        });

        edit.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            Booking booking = bookings.get(selectedIndex);

            EditBookingDialog editBookingForm = new EditBookingDialog(parent, request -> {
                bookingService.editBooking(booking.id(), request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        bookings.set(selectedIndex, result);
                        tableModel.setBookings(bookings);
                    });
                });
            }, booking);
            editBookingForm.setVisible(true);
        });

        delete.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            bookings.remove(selectedIndex);
            tableModel.setBookings(bookings);
        });
    }
}
