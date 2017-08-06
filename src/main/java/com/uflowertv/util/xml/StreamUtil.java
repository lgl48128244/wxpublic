package com.uflowertv.util.xml;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.omg.CORBA.IntHolder;

/**
 * 提供 IO 操作的工具类
 * @version 1.0
 * @author explorer
 */
public final class StreamUtil {
	
	public static final int BUFFER_SIZE = 4096;
  /**
   * 将输入流复制到输出流
   * @param is
   * @param os
   * @throws IOException
   */
  public final static void write(InputStream is, OutputStream os) throws
      IOException {
    BufferedInputStream bis = new BufferedInputStream(is, 1024*4);
    int c = bis.read();
    while (c != -1) {
      os.write(c);
      c = bis.read();
    }
    //os.flush();
  }
  
  /**
   * 通过管道的方式进行文件拷贝，提高文件拷贝的速度
   * @param is
   * @param os
   * @throws IOException
   */
  public static void copyFile(FileInputStream is, FileOutputStream os)
			throws IOException {
		FileChannel inC = is.getChannel();
		FileChannel outC = os.getChannel();
		int length = 4096*4096;
		ByteBuffer b = null;
		while (true) {
			if (inC.position() == inC.size()) {
				return;
			}
			if ((inC.size() - inC.position()) < length) {
				length = (int) (inC.size() - inC.position());
			}
			b = ByteBuffer.allocateDirect(length);
			inC.read(b);
			b.flip();
			outC.write(b);
			outC.force(false);
		}
	}

  /**
	 * 将输入流按指定的编码转成字符串
	 * 
	 * @param is
	 * @param encoding
	 * @return
	 * @throws IOException
	 */
  public final static String toString(InputStream is, String encoding) throws
      IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    write(is, bos);
    if (encoding == null) {
      return new String(bos.toByteArray());
    }
    else {
      return new String(bos.toByteArray(), encoding);
    }

  }

  /**
   * 将输入流按默认的编码转成字符串
   * @param is
   * @return
   * @throws IOException
   */
  public final static String toString(InputStream is) throws IOException {
    return toString(is, null);
  }

  /**
   * 关闭输入流，忽略异常
   * @param is
   */
  public final static void closeQuitely(InputStream is) {
    try {
      if (is != null) {
        is.close();
      }
    }
    catch (IOException e) {
    }

  }

  /**
   * 关闭输出流，忽略异常
   * @param os
   */
  public final static void closeQuitely(OutputStream os) {
    try {
      if (os != null) {
        os.close();
      }
    }
    catch (IOException e) {
    }
  }

  /**
   * 半闭输入输出流，忽略异常
   * @param is
   * @param os
   */
  public final static void closeQuitely(InputStream is, OutputStream os) {
    closeQuitely(is);
    closeQuitely(os);
  }
  /***
   * 复制文件，并计算进度
   * @param in
   * @param out
   * @param totalSize:总大小
   * @param progress：进度（0--100）
   * @throws IOException
   */
  public static void copy(InputStream in, OutputStream out, long totalSize,
			IntHolder progress) throws IOException {
		try {
			long byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
				int tempprog = progress.value
						+ (int) ((100 * byteCount) / totalSize);
				if (tempprog >= 100) {
					progress.value = 100;
				} else {
					progress.value = tempprog;
				}
			}
			out.flush();
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
    
  public static void main(String[] args) throws IOException {
//	  long start = new Date().getTime();
//	  FileInputStream is = new FileInputStream("D:\\北京卫视-养生堂10210919.mp4");
//	  File dir = new File("X:\\mmfiles_2010/103/260/");
//		if(!dir.exists()) {
//			dir.mkdirs();
//		}
//		File dstFile = new File("D:\\华尔街第六集投资之道.jpg");
//		if(!dstFile.exists()) {
//			dstFile.createNewFile();
//		}
//		FileOutputStream os = new FileOutputStream(dstFile);
	  
//		StreamUtil.copyFile(is, os);
//		long end = new Date().getTime();
//		System.out.println((end-start)/(1000));
	  	
//	  	IOUtils.toByteArray(is);
  }
  
  
}
