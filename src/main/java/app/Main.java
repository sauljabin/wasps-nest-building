/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of WaspsNestBuilding.
 * <p>
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app;

import app.gui.ControllerViewApp;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {

    private static void loadFeatures() {
        try {
            Config.load();
            Translate.load();
        } catch (Exception e) {
            Log.error(Main.class, "loadFeatures()", e);
            System.exit(0);
        }
    }

    private static void loadSkin() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    Log.info(Main.class, "Skin: " + info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            Log.error(Main.class, "loadSkin()", e);
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        loadFeatures();
        SwingUtilities.invokeLater(() -> {
            if (Boolean.parseBoolean(Config.get("SKIN")))
                loadSkin();
            new ControllerViewApp();
        });
    }

}
