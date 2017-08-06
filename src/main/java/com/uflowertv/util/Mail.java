package com.uflowertv.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.util.mail.Mail     
 * 创建人：liguoliang 
 * 创建时间：2016年8月18日 下午12:40:43   
 * 修改人：
 * 修改时间：2016年8月18日 下午12:40:43   
 * 修改备注：   
 * @version   V1.0
 */
public class Mail {
   private static String to; // 收件人
   private static String from; // 发件人
   private static String host; // smtp主机
   private static String username; // 用户名
   private static String password; // 密码
   private static String subject; // 邮件主题
   private static String content; // 邮件正文
    // 配置文件路径
    private static String mailPath = "mail.properties";
	//日志记录对象
	private static Logger log = LoggerFactory.getLogger(Mail.class);
	
    public Mail() {
    	
    }
    static {
		// 类初始化后加载配置文件
		InputStream in = Mail.class.getClassLoader()
				.getResourceAsStream(mailPath);
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			log.error("load mail setting error,pleace check the file path:"
					+ mailPath);
			log.error(e.toString(), e);
		}
		host = props.getProperty("mail.server");
		from = props.getProperty("mail.sender");
		username = props.getProperty("mail.username");
		password = props.getProperty("mail.password");
		subject = props.getProperty("mail.subject");
		log.debug("load mail setting success,file path:" + mailPath);
	}

    /**
     * 把主题转换为中文
     * 
     * @param strText
     * @return
     */
    public static String transferChinese(String strText) {
        try {
        	strText=MimeUtility.encodeText(strText,MimeUtility.mimeCharset("gb2312"),null);
          //  strText = MimeUtility.encodeText(new String(strText.getBytes(),
          //          "gb2312"), "gb2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    /**
     * 发送邮件
     * 
     * @return 成功返回true，失败返回false
     */
    public static boolean sendMail() {
        // 构造mail session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        // 构造MimeMessage并设定基本的值，创建消息对象
        MimeMessage msg = new MimeMessage(session);
        // 设置消息内容
        try {
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = { new InternetAddress(to) };
			// 把邮件地址映射到Internet地址上
			/**
			 * setRecipient（Message.RecipientType type, Address
			 * address），用于设置邮件的接收者。<br>
			 * 有两个参数，第一个参数是接收者的类型，第二个参数是接收者地址。<br>
			 * 接收者类型可以是Message.RecipientType .TO，Message
			 * .RecipientType.CC和Message.RecipientType.BCC，TO表示主要接收人，CC表示抄送人
			 * ，BCC表示秘密抄送人。接收者与发送者一样，通常使用InternetAddress的对象。
			 */
			msg.setRecipients(Message.RecipientType.TO, address);
			// 设置邮件的标题
			subject = transferChinese(subject);
			msg.setSubject(subject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();
			
			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			// 设置邮件内容(纯文本格式)
			// mbpContent.setText(content);
			// 设置邮件内容(HTML格式)
			mbpContent.setContent(content, "text/html;charset=utf-8");
			// 向MimeMessage添加（Multipart代表正文）
			mp.addBodyPart(mbpContent);
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			// 设置邮件发送的时间。
			msg.setSentDate(new Date());
			// 发送邮件
			Transport.send(msg);
		} catch (AddressException e) {
			 log.error("服务器地址异常："+e.getMessage());
	         return false;
		} catch (MessagingException e) {
			 log.error("网络异常："+e.getMessage());
	         return false;
		}
        return true;
    }

	public static String getTo() {
		return to;
	}

	public static void setTo(String to) {
		Mail.to = to;
	}

	public static String getFrom() {
		return from;
	}

	public static void setFrom(String from) {
		Mail.from = from;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		Mail.host = host;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Mail.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Mail.password = password;
	}

	public static String getSubject() {
		return subject;
	}

	public static void setSubject(String subject) {
		Mail.subject = subject;
	}

	public static String getContent() {
		return content;
	}

	public static void setContent(String content) {
		Mail.content = content;
	}
}