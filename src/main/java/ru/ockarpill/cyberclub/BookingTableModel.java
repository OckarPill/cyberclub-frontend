package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Booking;

import javax.swing.table.AbstractTableModel;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class BookingTableModel extends AbstractTableModel {
    private List<Booking> bookings = Collections.emptyList();
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_COMPUTER_ID = 1;
    private static final int COLUMN_CUSTOMER_ID = 2;
    private static final int COLUMN_START = 3;
    private static final int COLUMN_END = 4;

    public void setBookings(List<Booking> bookings) {
        this.bookings = List.copyOf(bookings);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return bookings.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Booking booking = this.bookings.get(rowIndex);
        return switch (columnIndex) {
            case COLUMN_ID -> booking.id();
            case COLUMN_COMPUTER_ID -> booking.computer_id();
            case COLUMN_CUSTOMER_ID -> booking.customer_id();
            case COLUMN_START -> dtf(booking.start());
            case COLUMN_END -> dtf(booking.end());
            default -> null;
        };
    }

    private Object dtf(ZonedDateTime start) {
        return start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUMN_ID -> {
                return Integer.class;
            }
            case COLUMN_COMPUTER_ID -> {
                return Integer.class;
            }
            case COLUMN_CUSTOMER_ID -> {
                return Integer.class;
            }
            case COLUMN_START -> {
                return String.class;
            }
            case COLUMN_END -> {
                return String.class;
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case COLUMN_ID -> "ID";
            case COLUMN_COMPUTER_ID -> "Computer ID";
            case COLUMN_CUSTOMER_ID -> "Customer ID";
            case COLUMN_START -> "Time start";
            case COLUMN_END -> "Time end";
            default -> null;
        };
    }

}
