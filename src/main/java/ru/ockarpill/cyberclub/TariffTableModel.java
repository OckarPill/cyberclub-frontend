package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Tariff;

import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;

public class TariffTableModel extends AbstractTableModel {
    private List<Tariff> tariffs = Collections.emptyList();
    private static final int COLUMN_ID = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_HOURS = 2;
    private static final int COLUMN_COST = 3;

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = List.copyOf(tariffs);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return tariffs.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tariff tariff = this.tariffs.get(rowIndex);
        switch (columnIndex) {
            case COLUMN_ID -> {
                return tariff.id();
            }
            case COLUMN_NAME -> {
                return tariff.name();
            }
            case COLUMN_HOURS -> {
                return formatValue(tariff.hours());
            }
            case COLUMN_COST -> {
                return formatValue(tariff.cost());
            }
            default -> {
                return null;
            }
        }
    }

    private String formatValue(int value) {
        String bgColor = value < 0 ? "red" : "NONE";

        return "<html><font bgcolor='" + bgColor + "'>" + value + "</font></html>";
    }



    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case COLUMN_ID, COLUMN_HOURS, COLUMN_COST -> {
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
            case COLUMN_HOURS -> "Hours";
            case COLUMN_COST -> "Cost";
            default -> null;
        };
    }
}
