package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private List<User> users = Collections.emptyList();
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_AGE = 2;

    public void setUsers(List<User> users) {
        this.users = List.copyOf(users);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = this.users.get(rowIndex);
        return switch (columnIndex) {
            case COLUMN_ID -> user.id();
            case COLUMN_NAME -> user.name();
            case COLUMN_AGE -> user.age();
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUMN_ID -> {
                return Integer.class;
            }
            case COLUMN_AGE -> {
                return Integer.class;
            }
            case COLUMN_NAME -> {
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
            case COLUMN_NAME -> "Name";
            case COLUMN_AGE -> "Age";
            default -> null;
        };
    }

}
