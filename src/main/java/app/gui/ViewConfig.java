/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.gui;

import app.Config;
import app.Translate;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.Vector;

public class ViewConfig extends JDialog {

    public ViewConfig() {
        setSize(400, 250);
        setTitle(Config.get("APP_NAME"));

        JPanel panel = new JPanel(new MigLayout());

        JScrollPane scrollPanel = new JScrollPane();

        panel.add(scrollPanel);

        add(panel);
        JTable table = new JTable();
        scrollPanel.setViewportView(table);
        new ConfigModelTable(table);
        setLocationRelativeTo(this);
        setModal(true);
        setVisible(true);
    }

    public class ConfigModelTable extends AbstractTableModel {

        private Vector<String> titles;
        private Vector<String> keys;
        private JTable table;

        public ConfigModelTable(JTable table) {
            titles = new Vector<String>();
            titles.add("#");
            titles.add(Translate.get("GUI_NAME"));
            titles.add(Translate.get("GUI_VALUE"));
            keys = Config.getKeys();
            Collections.sort(keys);

            this.table = table;
            this.table.setModel(this);
            this.table.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        @Override
        public int getColumnCount() {
            return titles.size();
        }

        @Override
        public int getRowCount() {
            return keys.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return rowIndex + 1;
                case 1:
                    return keys.get(rowIndex);
                case 2:
                    return Config.get(keys.get(rowIndex));
            }
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return titles.get(column);
        }

    }
}
