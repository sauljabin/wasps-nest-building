/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of WaspsNestBuilding.
 * 
 * WaspsNestBuilding is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import app.util.UtilFile;

public class Translate {

	private static Properties properties = new Properties();

	public static void load() throws FileNotFoundException, IOException {
		load(Config.get("TRANSLATE"));
	}

	public static void load(String language) throws FileNotFoundException, IOException {
		properties.load(new InputStreamReader(new FileInputStream(String.format("%s%s", Config.get("TRANSLATE_PATH"), language)), "UTF-8"));
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static Enumeration<Object> getKeys() {
		return properties.keys();
	}

	public static String[] languages() {
		File[] files = UtilFile.files(Config.get("TRANSLATE_PATH"));
		String[] filesName = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			filesName[i] = files[i].getName();
		}
		return filesName;
	}

}
