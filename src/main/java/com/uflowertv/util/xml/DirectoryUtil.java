package com.uflowertv.util.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * String Utility Class This is used to operate directory programmatically
 * 
 * <p>
 * <a h ref="DirectoryUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:xiachaojun@gmail.com">chaojun xia</a>
 */
public class DirectoryUtil {
	/**
	 * try to know whether it's a directory
	 * 
	 * @param strDir
	 *            String
	 * @return boolean
	 */
	public static boolean existsDirectory(String strDir) {
		File file = new File(strDir);
		return file.exists() && file.isDirectory();
	}

	/**
	 * try to create the directory together with its sub dirs
	 * 
	 * @param strDir
	 *            String
	 * @return boolean
	 */
	public static boolean forceDirectory(String strDir) {
		File file = new File(strDir);
		file.mkdirs();
		return existsDirectory(strDir);
	}

	/**
	 * return the file path in correct format
	 * 
	 * @param strPath
	 *            String
	 * @return String
	 */
	public static String fixPath(String strPath) {
		strPath = (strPath == null ? "" : strPath);
		strPath = strPath.replaceAll("\\\\", "/");
		if (!strPath.endsWith("/")) {
			strPath += "/";
		}
		return strPath;
	}

	public static boolean copy(String sourcedir, String destdir)
			throws IOException {
		if (sourcedir == null || destdir == null)
			return false;
		File filein = new File(sourcedir);
		File fileout = new File(destdir);
		fileout.mkdirs();
		File[] files = filein.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				write(files[i], destdir);
			}
			if (files[i].isDirectory()) {
				copy(sourcedir + "/" + files[i].getName(), destdir + "/"
						+ files[i].getName());
			}
		}
		return true;
	}

	private static void write(File sourfile, String destdir) {
		try {
			FileInputStream ins = new FileInputStream(sourfile);
			FileOutputStream ous = new FileOutputStream(destdir + "/"
					+ sourfile.getName());
			FileUtil.copy(ins, ous);
			ous.flush();
			ous.close();
			ins.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getWinDir(String dir) {
		dir = dir.replaceAll("/", "\\\\");
		dir = dir.replaceAll("\\\\\\\\", "\\\\");
		return dir;
	}

	public static void main(String[] args) {
		String dir = "O:\\/mmfiles_2008/155/206/";
		System.err.println(getWinDir(dir));
	}
}