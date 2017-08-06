package com.uflowertv.test;

import java.io.IOException;


/**
 * @author Javen
 * @Email zyw205@gmail.com
 * 
 */
public class Test {
    public static void main(String[] args) throws IOException {
    	System.out.println("pic144705838583001014.jpg".length());
    	System.out.println(System.currentTimeMillis());
        /*
        Mail.setTo("275018155@qq.com");
        Mail.setContent("测试发邮件");
        if (Mail.sendMail()) {
            System.out.println(" 发送成功");
        }*/
    	/* String path = Test.class.getClassLoader().getResource("userinfo.json").getPath();
    	 File file = new File(path);
    	 BufferedReader reader = new BufferedReader(new FileReader(file));
	     String temp = null;
	     StringBuffer json = new StringBuffer();
	     //一次读入一行，直到读入null为文件结束
	     while ((temp = reader.readLine()) != null) {
	    	 json.append(temp);
	     }
	     reader.close();
	     Follower follower = JsonUtils.json2Bean(json.toString(), Follower.class);
	     System.out.println(follower.toString());*/
    }
}