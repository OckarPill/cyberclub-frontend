package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.Session;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SessionPane extends JPanel {
    private final JFrame parent;
    private final JButton dialogbutton = new JButton("Add session");
    private final JButton edit = new JButton("Edit");
    private final JButton delete = new JButton("Delete");
    private final SessionService sessionService = new SessionService();

    public SessionPane(JFrame parent) {
        super(new GridBagLayout());
        this.parent = parent;
        List<Session> sessions = new ArrayList<>();

        edit.setEnabled(false);
        delete.setEnabled(false);

        SessionTableModel tableModel = new SessionTableModel();

        sessionService.getSessions().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                sessions.addAll(result);
                tableModel.setSessions(sessions);
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
            EditSessionDialog addSessionForm = new EditSessionDialog(parent, request -> {
                sessionService.addSession(request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        sessions.add(result);
                        tableModel.setSessions(sessions);
                    });
                });
            }, null);
            addSessionForm.setVisible(true);
        });

        edit.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            Session session = sessions.get(selectedIndex);

            EditSessionDialog editSessionForm = new EditSessionDialog(parent, request -> {
                sessionService.editSession(session.id(), request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        sessions.set(selectedIndex, result);
                        tableModel.setSessions(sessions);
                    });
                });
            }, session);
            editSessionForm.setVisible(true);
        });

        delete.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            sessions.remove(selectedIndex);
            tableModel.setSessions(sessions);
        });
    }
}
