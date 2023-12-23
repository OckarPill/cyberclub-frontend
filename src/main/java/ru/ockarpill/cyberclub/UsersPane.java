package ru.ockarpill.cyberclub;

import ru.ockarpill.cyberclub.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UsersPane extends JPanel {
    private final JFrame parent;
    private final JButton dialogbutton = new JButton("Add user");
    private final JButton edit = new JButton("Edit");
    private final JButton delete = new JButton("Delete");
    private final UserService userService = new UserService();
    public UsersPane (JFrame parent) {
        super(new GridBagLayout());

        this.parent = parent;
        ArrayList<User> users = new ArrayList<>();//список пользователей

        edit.setEnabled(false);//крч чтоб кнопки delete и edit выглядели недоступными, если не выделен пользователь
        delete.setEnabled(false);

        UserTableModel tableModel = new UserTableModel(); //подрубаем табличку пользователей

        userService.getUsers().thenAccept(result -> {
            SwingUtilities.invokeLater(() -> {
                users.addAll(result);
                tableModel.setUsers(users);
            });
        });
        
        JTable table = new JTable(tableModel); //создаем новую табличку

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //чтоб выделялся только один пользователь в табличке
        ListSelectionModel selectionModel = table.getSelectionModel(); //выделение пользователя
        selectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting())                  //только финальный результат выделения (без каких-либо промеж. сост.)?
                return;
            edit.setEnabled(selectionModel.getSelectedItemsCount() != 0); //выделение только один раз ?
            delete.setEnabled(selectionModel.getSelectedItemsCount() != 0);
        });

        GridBagConstraints constraints = new GridBagConstraints(); //параметры отображения

        constraints.gridx = 0;
        constraints.weightx = 1;

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.EAST; //закрепление кнопок-инструментов справа

        JPanel tools = new JPanel(new FlowLayout());

        tools.add(dialogbutton);
        tools.add(edit);
        tools.add(delete);

        this.add(tools, constraints);

        constraints.weighty = 1;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.fill = GridBagConstraints.BOTH;     //при наличии свободного места заполнение его и по горизонтали и по вертикали
        this.add(new JScrollPane(table), constraints);  //добавление таблички + добавление прокрутки

        dialogbutton.addActionListener(e -> {  //нажимабельность кнопки add user
            EditUserDialog addUserForm = new EditUserDialog(parent, request -> {
                userService.addUser(request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        users.add(result);
                        tableModel.setUsers(users);
                    });
                });
            }, null);
            addUserForm.setVisible(true);
        });

        edit.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            User user = users.get(selectedIndex);

            EditUserDialog editUserForm = new EditUserDialog(parent, request -> {
                userService.editUser(user.id(), request).thenAccept(result -> {
                    SwingUtilities.invokeLater(() -> {
                        users.set(selectedIndex, result);
                        tableModel.setUsers(users);
                    });
                });
            }, user);
            editUserForm.setVisible(true);
        });

        delete.addActionListener(e -> {
            int selectedIndex = selectionModel.getSelectedIndices()[0];
            users.remove(selectedIndex);
            tableModel.setUsers(users);
        });
    }
}
