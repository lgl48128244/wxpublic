package com.uflowertv.util.xml;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUtil {
	
	public static final int BUFFER_SIZE = 4096;
	
	public static final String algorithm_md5 = "MD5";
	
	public static final String algorithm_sha = "SHA";
	
	private static final Log log = LogFactory.getLog(FileUtil.class);

	public static String LoadXmlFile(String filePath) {

		String str = null;
		if (filePath == null)
			return str;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "utf-8"));
			String s = reader.readLine();

			StringBuffer sb = new StringBuffer();

			while (s != null) {
				sb.append(s);
				sb.append("\r\n");
				s = reader.readLine();
			}

			reader.close();
			str = sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String LoadXmlFile(File file) {
		String str = null;
		if (!file.exists())
			return str;
		java.io.BufferedReader reader;
		StringBuffer sb;
		try {
			reader = new java.io.BufferedReader(new java.io.InputStreamReader(new FileInputStream(file), "gb2312"));
			String s = reader.readLine();
			sb = new StringBuffer();
			while (s != null) {
				sb.append(s);
				sb.append("\r\n");
				s = reader.readLine();
			}
			//logger.info(sb.toString());
			reader.close();
			str = sb.toString();
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return str;
	}
	
	public static Boolean saveStringtoFile(String path, String content,
			String encode) {
		try {
			if (encode == null || encode.equals("")) {
				encode = "utf-8";
			}
			File f = new File(path);
			f.deleteOnExit();
			f.createNewFile();

			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f), encode));
			output.write(content);
			output.close();
		} catch (Exception e) {
			log.error("保存文件" + path + "异常", e);
			return Boolean.valueOf(false);
		}
		return Boolean.valueOf(true);
	}

	public static boolean delete(String fileName) {
		if (fileName == null)
			return false;
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		}
		if (file.isFile()) {
			return deleteFile(fileName);
		}
		return deleteDirectory(fileName);
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if ((file.isFile()) && (file.exists())) {
			file.delete();

			return true;
		}

		return false;
	}

	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);

		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {

			return false;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			} else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}
		if (!flag) {

			return false;
		}

		if (dirFile.delete()) {

			return true;
		}

		return false;
	}

	public static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		} finally {
			try {
				in.close();
			} catch (IOException ex) {

			}
			try {
				out.close();
			} catch (IOException ex) {

			}
		}
	}

	public static String copyFile(String sourceUrl, String targetUrl) throws Exception {
		log.info("开始拷贝文件,源文件：" + sourceUrl +";目标文件：" + targetUrl);	
		FileInputStream fis = null;
		FileOutputStream fos = null;	
		try {
			File sourceFile = new File(sourceUrl);
			if(!sourceFile.exists()) {
				throw new Exception("源文件"+sourceUrl+"不存在");
			}
			int readLength = 0;
			File targetFile = new File(targetUrl);
			if (targetFile.exists()) {
				targetFile.delete();
			} else {
				targetFile.getParentFile().mkdirs();
			}
			fis = new FileInputStream(sourceUrl);
			fos = new FileOutputStream(targetFile);
			byte[] bytes = new byte[1024*128];
			while ((readLength = fis.read(bytes)) > 0) {
				fos.write(bytes, 0, readLength);
			}
			log.info("文件拷贝结束");
		} catch (Exception e) {
			log.error("拷贝文件异常",e);
			throw new Exception(e);
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return targetUrl;
	}
	
	/**
	 * @param sourceUrl
	 * @param targetUrl
	 * @param check
	 *            true 验证文件是否全
	 * @return
	 * @throws Exception
	 */
	public static String copyFile(String sourceUrl, String targetUrl,
			Boolean check) throws Exception {
		log.info("开始拷贝文件,源文件：" + sourceUrl + ";目标文件：" + targetUrl);
		File sourceFile = new File(sourceUrl);
		File targetFile = new File(targetUrl);
		// 验证源文件是否正在拷贝中
		if (check) {
			long size1 = sourceFile.length();
			long size2 = 0;
			do {
				log.info("size1:" + size1);
				Thread.sleep(3000);
				size2 = sourceFile.length();
				log.info("size2:" + size2);
			} while (size1 != size2);
		}
		// end
		FileInputStream fis = null;
		FileOutputStream fos = null;
		byte[] bytes = new byte[32 * 1024];
		try {
			int readLength = 0;
			if (targetFile.exists()) {
				targetFile.delete();
			} else {
				targetFile.getParentFile().mkdirs();
			}
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(targetFile);
			while ((readLength = fis.read(bytes)) > 0) {
				fos.write(bytes, 0, readLength);
			}
			log.info("文件拷贝结束");
		} catch (Exception e) {
			log.error("拷贝文件异常", e);
			throw new Exception(e);
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return targetUrl;
	}

	public static String readNetFile(String file) {
		try {
			BufferedInputStream result = new BufferedInputStream(
					(new URL(file)).openStream());
			byte[] cont = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int conlen;
			while ((conlen = result.read(cont)) >= 0) {
				baos.write(cont, 0, conlen);
			}
			result.close();
			return new String(new String(baos.toByteArray()).getBytes(),
					"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String read(String file) {
		try {
			BufferedInputStream result = new BufferedInputStream(
					new FileInputStream(file));
			byte[] cont = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int conlen;
			while ((conlen = result.read(cont)) >= 0) {
				baos.write(cont, 0, conlen);
			}
			result.close();
			return new String(baos.toByteArray());
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * 文件拷贝
	 */
	// public static void copyFile(String sourceFilePath,String
	// sourceFileName,String targetFilePath,String targetFileName) throws
	// Exception {
	// FileInputStream is = null;
	// FileOutputStream os = null;
	// try {
	// String sourceFile = sourceFilePath + sourceFileName;
	// sourceFile = sourceFile.replaceAll("/", "\\\\");
	// logger.info("源文件：" + sourceFile +";目标文件：" + targetFilePath + "/" +
	// targetFileName);
	// long start = System.currentTimeMillis();
	// is = new FileInputStream(sourceFile);
	// File targetPath = new File(targetFilePath);
	// if(!targetPath.exists()) { //目标路径不存在,创建
	// targetPath.mkdirs();
	// }
	// File targetFile = new File(targetFilePath + "/" + targetFileName);
	// // 目标文件存在则先将其删除
	// targetFile.deleteOnExit();
	// targetFile.createNewFile();
	// os = new FileOutputStream(targetFile);
	// // StreamUtil.copyFile(is, os);
	// copy(is, os);
	// logger.info("拷贝文件完成，用时（秒）:" + (System.currentTimeMillis()-start)/1000);
	// }catch(Exception e) {
	// logger.error("拷贝文件异常:" + e.getMessage());
	// throw new Exception("拷贝文件异常",e);
	// }finally {
	// try {
	// is.close();
	// os.close();
	// }catch(Exception e) {
	// // 不处理
	// }
	// }
	//
	// }

	/**
	 * 获取文件校验码
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	// public static String getMD5Code(String filePath) throws IOException {
	// InputStream iStream = new FileInputStream(filePath);
	// return DigestUtils.md5Hex(StreamUtil.toByteArray(iStream));
	// }

	public static String verify(String algorithm, InputStream in)
			throws IOException {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			log.debug("start computing...");
			long st = System.currentTimeMillis();
			while ((bytesRead = in.read(buffer)) != -1) {
				md.update(buffer, 0, bytesRead);
			}
			log.debug("end   computing...");
			long et = System.currentTimeMillis();
			log.debug("time cost: " + (int) ((et - st) / 1000));
			return bytesToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			log.error(algorithm, e);
			return "";
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
				log.warn("Could not close InputStream", ex);
			}
		}
	}

	public static String verify(InputStream in) throws IOException {
		return verify(algorithm_md5, in);
	}

	public static String verify(String algorithm, String fileName)
			throws IOException {
		FileInputStream fis = new FileInputStream(new File(fileName));
		return verify(algorithm, fis);
	}

	public static String verify(String fileName) throws IOException {
		return verify(algorithm_md5, fileName);
	}

	private static String bytesToString(byte[] data) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] temp = new char[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			byte b = data[i];
			temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
			temp[i * 2 + 1] = hexDigits[b & 0x0f];
		}
		return new String(temp);
	}

	public static void main(String[] args) throws Exception {
		// copyFile("\\\\10.3.3.165\\live\\myeclipse-9.0-offline-installer-windows.exe","D:\\project\\temp\\myeclipse-9.0-offline-installer-windows.exe",true);
		int i = 0;
		do {
			i++;
			System.out.println(i);
		} while (i != 5);
	}

}
