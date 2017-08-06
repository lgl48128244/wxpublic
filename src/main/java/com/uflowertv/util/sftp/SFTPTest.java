package com.uflowertv.util.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.ChannelSftp;

public class SFTPTest {

    public static SFTPChannel getSFTPChannel() {
        return new SFTPChannel();
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String src = "D:\\迅雷下载\\[迅雷下载www.2tu.cc]终极斗士3：赎罪.BD1024超清中英双字.mkv"; // 本地文件名
        String dst = "/usr/local/tomcat_8088/webapps/img_web/feedback/[迅雷下载www.2tu.cc]终极斗士3：赎罪.BD1024超清中英双字.mkv"; // 目标文件名
        SFTPChannel channel = getSFTPChannel();
        ChannelSftp chSftp = channel.getChannel(60000);
        File file = new File(src);
        long fileSize = file.length();
        
         //代码段1
        OutputStream out = chSftp.put(dst,new FileProgressMonitor(fileSize), 1); // 使用OVERWRITE模式
        byte[] buff = new byte[1024 * 2048]; // 设定每次传输的数据块大小为256KB
        int read;
        if (out != null) {
            InputStream is = new FileInputStream(src);
            do {
                read = is.read(buff, 0, buff.length);
                if (read > 0) {
                    out.write(buff, 0, read);
                }
                out.flush();
            } while (read >= 0);
        }
        
       // chSftp.put(src, dst,new FileProgressMonitor(fileSize), ChannelSftp.OVERWRITE); // 代码段2
        
        // chSftp.put(new FileInputStream(src), dst, ChannelSftp.OVERWRITE); // 代码段3
        
        chSftp.quit();
        channel.closeChannel();
    }
}