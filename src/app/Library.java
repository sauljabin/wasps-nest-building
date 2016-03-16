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
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Vector;

public class Library {

	/**
	 * https://java3d.java.net/binary-builds.html
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws FileNotFoundException
	 */
	public static void load() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, FileNotFoundException {

		String path = "";

		if (Library.is64Bit() && Library.isLinux())
			path = Config.get("LIB_PATH_LINUX_64");
		else if (Library.is64Bit() && Library.isWindows())
			path += Config.get("LIB_PATH_WINDOWS_64");
		else if (Library.is32Bit() && Library.isLinux())
			path = Config.get("LIB_PATH_LINUX_32");
		else if (Library.is32Bit() && Library.isWindows())
			path += Config.get("LIB_PATH_WINDOWS_32");
		else
			throw new FileNotFoundException("Api java 3D for arch not found");

		File file = new File(path);

		Field field = ClassLoader.class.getDeclaredField("usr_paths");
		field.setAccessible(true);
		String[] paths = (String[]) field.get(null);

		Vector<String> pathsList = new Vector<String>();
		Collections.addAll(pathsList, paths);
		pathsList.add(file.getAbsolutePath());
		field.set(null, pathsList.toArray(new String[pathsList.size()]));

		System.setProperty("java.library.path", System.getProperty("java.library.path") + File.pathSeparator + file.getAbsolutePath());
	}

	public static boolean isLinux() {
		return Config.get("OS").toLowerCase().contains("linux");
	}

	public static boolean isWindows() {
		return Config.get("OS").toLowerCase().contains("windows");
	}

	public static boolean is32Bit() {
		Vector<String> strings = new Vector<String>();
		strings.add("x86");
		strings.add("i386");

		for (String string : strings) {
			if (Config.get("OS_ARCH").toLowerCase().contains(string))
				return true;
		}

		return false;
	}

	public static boolean is64Bit() {
		Vector<String> strings = new Vector<String>();
		strings.add("x64");
		strings.add("amd64");

		for (String string : strings) {
			if (Config.get("OS_ARCH").toLowerCase().contains(string))
				return true;
		}

		return false;
	}

}
