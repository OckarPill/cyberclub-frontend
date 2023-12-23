package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Session;

import javax.swing.table.AbstractTableModel;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public class SessionTableModel extends AbstractTableModel {
    private List<Session> sessions = Collections.emptyList();
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_COMPUTER_ID = 1;
    private static final int COLUMN_CUSTOMER_ID = 2;
    private static final int COLUMN_TARIFF_ID = 3;
    private static final int COLUMN_START = 4;
    private static final int COLUMN_END = 5;

    public void setSessions(List<Session> sessions) {
        this.sessions = List.copyOf(sessions);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return sessions.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }
    private Object dtf(ZonedDateTime start) {
        return start.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Session session = this.sessions.get(rowIndex);
        return switch (columnIndex) {
            case COLUMN_ID -> session.id();
            case COLUMN_COMPUTER_ID -> session.computer_id();
            case COLUMN_CUSTOMER_ID -> session.customer_id();
            case COLUMN_TARIFF_ID -> session.tariff_id();
            case COLUMN_START -> dtf(session.start());
            case COLUMN_END -> dtf(session.end());
            default -> null;
        };
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
            case COLUMN_TARIFF_ID -> {
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
            case COLUMN_TARIFF_ID -> "Tariff ID";
            case COLUMN_START -> "Time start";
            case COLUMN_END -> "Time end";
            default -> null;
        };
    }

}
