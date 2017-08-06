package com.uflowertv.util;

import java.security.MessageDigest;

/** 
 * 对密码进行加密和验证的类
 */
public class CipherUtil{
    
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f","g","h","i","j","k","l","m","n","o","p","q","r","s",
        "t","u","v","w","x","y","z"
    };
    
    
  /**
   * 生成MD5
   * @Title: generator
   * @Description: TODO(这里用一句话描述这个方法的作用)
   * @param content
   * @return
   */
    public static String generator(String content){
        return encodeByMD5(content);
    }
    
    
    /**
     * 验证内容是否正确
     * @param md5    加密后的内容
     * @param content    输入的内容
     * @return    验证结果，TRUE:正确 FALSE:错误
     */
    public static boolean validate(String md5, String content){
        if(md5.equals(encodeByMD5(content))){
            return true;
        } else{
            return false;
        }
    }
    
    /**  对字符串进行MD5加密     */
    private static String encodeByMD5(String originString){
        if (originString != null){
            try{
                //创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
    
    /** 
     * 转换字节数组为十六进制字符串
     * @param     字节数组
     * @return    十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    /** 将一个字节转化成十六进制形式的字符串     */
    private static String byteToHexString(byte b){
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}