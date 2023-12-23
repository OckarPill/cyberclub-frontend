package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Computer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ComputersPane extends JPanel {
    private final JFrame parent;
    private final JButton dialogbutton = new JButton("Add computer");
    private final JButton edit = new JButton("Edit");
    private final JButton delete = new JButton("Delete");
    private final ComputerService computerService = new ComputerService();

    public ComputersPane(JFrame parent) {
        super(new GridBagLayout());
        this.parent = parent;
        List<Computer> computers = new ArrayList<>();

        edit.setEnabled(false);
        delete.setEnabled(false);

        ComputerTableModel tableModel = new ComputerTableModel();

        computerService.getComputers().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                computers.addAll(result);
                tableModel.setComputers(computers);
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
            EditComputerDialog addComputerForm = new EditComputerDialog(parent, request -> {
                computerService.addComputer(request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        computers.add(result);
                        tableModel.setComputers(computers);
                    });
                });
            }, null);
            addComputerForm.setVisible(true);
        });

        edit.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            Computer computer = computers.get(selectedIndex);

            EditComputerDialog editComputerForm = new EditComputerDialog(parent, request -> {
                computerService.editComputer(computer.id(), request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        computers.set(selectedIndex, result);
                        tableModel.setComputers(computers);
                    });
                });
            }, computer);
            editComputerForm.setVisible(true);
        });

        delete.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            computers.remove(selectedIndex);
            tableModel.setComputers(computers);
        });
    }
}
