/**
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 *		SAUL PIÑA - SAULJP07@GMAIL.COM
 *		2014
 */

package app;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import app.gui.ControllerViewApp;

public class Main {

	private static void loadFeatures() {
		try {
			Config.load();
			Config.save();
			Translate.load();
			Library.load();
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (Boolean.parseBoolean(Config.get("SKIN")))
					loadSkin();
				new ControllerViewApp();
			}
		});
	}

}
