package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Tariff;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TariffsPane extends JPanel {
    private final JFrame parent;
    private final JButton dialogbutton = new JButton("Add tariff");
    private final JButton edit = new JButton("Edit");
    private final JButton delete = new JButton("Delete");
    private final TariffService tariffService = new TariffService();

    public TariffsPane(JFrame parent) {
        super(new GridBagLayout());

        this.parent = parent;
        ArrayList<Tariff> tariffs = new ArrayList<>();

        edit.setEnabled(false);
        delete.setEnabled(false);

        TariffTableModel tableModel = new TariffTableModel();

        tariffService.getTariffs().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                tariffs.addAll(result);
                tableModel.setTariffs(tariffs);
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
            EditTariffDialog addTariffForm = new EditTariffDialog(parent, request -> {
                tariffService.addTariff(request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        tariffs.add(result);
                        tableModel.setTariffs(tariffs);
                    });
                });
            }, null);
            addTariffForm.setVisible(true);
        });

        edit.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            Tariff tariff = tariffs.get(selectedIndex);

            EditTariffDialog editTariffForm = new EditTariffDialog(parent, request -> {
                tariffService.editTariff(tariff.id(), request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        tariffs.set(selectedIndex, result);
                        tableModel.setTariffs(tariffs);
                    });
                });
            }, tariff);
            editTariffForm.setVisible(true);
        });

        delete.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            tariffs.remove(selectedIndex);
            tableModel.setTariffs(tariffs);
        });
    }
}
