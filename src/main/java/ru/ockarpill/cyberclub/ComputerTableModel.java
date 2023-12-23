package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Computer;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

public class ComputerTableModel extends AbstractTableModel {
    private List<Computer> computers = Collections.emptyList();
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NUMBER = 1;
    private static final int COLUMN_STATUS = 2;

    public void setComputers(List<Computer> computers) {
        this.computers = List.copyOf(computers);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return computers.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Computer computer = this.computers.get(rowIndex);
        return switch (columnIndex) {
            case COLUMN_ID -> computer.id();
            case COLUMN_NUMBER -> computer.number();
            case COLUMN_STATUS -> computer.booking() != null ? computer.booking().toString() : computer.session() != null ? computer.session().toString() : null;
            default -> null;
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUMN_ID -> {
                return Integer.class;
            }
            case COLUMN_NUMBER -> {
                return Integer.class;
            }
            case COLUMN_STATUS -> {
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
            case COLUMN_NUMBER -> "Number";
            case COLUMN_STATUS -> "Status";
            default -> null;
        };
    }

}
