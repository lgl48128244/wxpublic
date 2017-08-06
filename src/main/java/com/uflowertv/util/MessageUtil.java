package com.uflowertv.util;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/*********************************
*
* 短信发送接口调用类
*
* @author YangJin
*
********************************/
@SuppressWarnings("all")
public class MessageUtil {
   
   public static void main(String[] args) throws Exception {
       JSONObject jsonObj = new JSONObject();
       jsonObj.put("op", "Sms.send");
       jsonObj.put("apiKey", ConstantHolder.APIKEY);
       jsonObj.put("ts", System.currentTimeMillis());
       jsonObj.put("phone", "18201508823");
       jsonObj.put("templateId", ConstantHolder.TEMPLATEID);
       jsonObj.put("content", "1234");
       jsonObj.put("taskId", System.currentTimeMillis() );//不超过64位长度的唯一字符串，通过和recvRPT获取的结果里的teskid关联，确定发送的信息是否收到。
       jsonObj.put("extNum", "" );
       jsonObj.put("sig", getSig(jsonObj, ConstantHolder.SECRETKEY));
   
       String result = MessageUtil.transmessage(ConstantHolder.SMSURL, jsonObj.toString());
       System.out.println(result);
       
   }
   /**
    * 
    * @param phone
    * @param validate
    * @return
    */
   public static String getMsgJson(String phone,String validate){
	   JSONObject jsonObj = new JSONObject();
       jsonObj.put("op", "Sms.send");
       jsonObj.put("apiKey", ConstantHolder.APIKEY);
       jsonObj.put("ts", System.currentTimeMillis());
       jsonObj.put("phone", phone);
       jsonObj.put("templateId", ConstantHolder.TEMPLATEID);
       jsonObj.put("content", validate);
       jsonObj.put("taskId", System.currentTimeMillis() );//不超过64位长度的唯一字符串，通过和recvRPT获取的结果里的teskid关联，确定发送的信息是否收到。
       jsonObj.put("extNum", "" );
       jsonObj.put("sig", getSig(jsonObj, ConstantHolder.SECRETKEY));
   
       return jsonObj.toString();
	}
   
   /**
    * 生成验证码
    * @return
    */
   public static String madeVali() {
       Random r = new Random();
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < 6; i++) {
           sb.append(r.nextInt(9));
       }
       return sb.toString();
   }
   
   /**
    * 
    * @param requrl
    * @param paras
    * @return
    */
   public static String transmessage(String requrl, String paras) {
       String ret = null;
       try {
           URL url = new URL(requrl);
           HttpURLConnection httpConn = (HttpURLConnection) url
                   .openConnection();
           httpConn.setConnectTimeout(30000);
           httpConn.setReadTimeout(30000);
           httpConn.setDoOutput(true);
           httpConn.setDoInput(true);
           httpConn.setRequestMethod("POST");
           DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());

           String reqstr = paras;
           out.write(reqstr.getBytes("UTF-8"));
           out.flush();
           out.close();
           InputStream stream = httpConn.getInputStream();

           DataInputStream in = new DataInputStream(stream);
           byte[] bin = null;
           byte[] inc = new byte[1024];
           int datelength = 0;
           int insize = 0;
           while ((insize = in.read(inc)) != -1) {
               int oldlength = datelength;
               datelength += insize;
               byte[] oldbin = new byte[datelength];
               for (int i = 0; i < oldlength; i++)
                   oldbin[i] = bin[i];
               for (int i = oldlength; i < datelength; i++)
                   oldbin[i] = inc[i - oldlength];
               bin = oldbin;
           }
           ret = new String(bin, "UTF8");
           in.close();
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return ret;
   }


/**
    * 获取md5加密字符串 32位长
    *
    * @param 明文   
   
    * @return String 密文
    */
private final static String MD5(String s) {
     char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd','e', 'f'};

     try {
       byte[] strTemp = s.getBytes();
       MessageDigest mdTemp = MessageDigest.getInstance("MD5");
       mdTemp.update(strTemp);
       byte[] md = mdTemp.digest();
       int j = md.length;
       char str[] = new char[j * 2];
       int k = 0;
       for (int i = 0; i < j; i++) {
         byte byte0 = md[i];
         str[k++] = hexdigits[byte0 >>> 4 & 0xf];
         str[k++] = hexdigits[byte0 & 0xf];
       }
       return new String(str);
     }
     catch (Exception e) {
       return null;
     }
   }

   /**
    * 获取Testin平台数字签名
    *
    * @param reqjson   请求的json
    * @param secretkey Testin平台密钥
    * @return String
    */
   protected static String getSig(JSONObject reqjson, String secretkey) {
       String result = null;

       if (reqjson == null || secretkey == null) {
           return result;
       }

       try {
           StringBuffer sb = new StringBuffer();
           Iterator itKeys = reqjson.keys();
           TreeSet setKeys = new TreeSet();
           while (itKeys.hasNext()) {
               setKeys.add(itKeys.next());
           }
           Iterator sortedIt = setKeys.iterator();
           for (Iterator itKey = sortedIt; sortedIt.hasNext();) {
               String key = (String)itKey.next();
               if ("sig".equals(key)) {
                   continue;
               }

               Object obj = reqjson.get(key);

               sb.append(key);
               sb.append("=");

               if (obj instanceof JSONObject || obj instanceof JSONArray) {
                   String value = getSortJsonStr(obj);
                   sb.append(value);
               } else {
                   String value = reqjson.getString(key);
                   sb.append(value);
               }

           }
           sb.append(secretkey);

       
           result = MD5(sb.toString());
       } catch (Exception e) {
           e.printStackTrace();
       }

       return result;
   }

   /**
    * 递归获取排过序的json信息
    *
    * @param objJson json排序
    * @return String
    */
   private static String getSortJsonStr(Object objJson) {
       StringBuffer result = new StringBuffer();

       try {

           if (objJson instanceof JSONArray) {
               result.append("[");
               for (int i = 0; i < ((JSONArray) objJson).size(); i++) {
                   Object arrayMember = ((JSONArray) objJson).get(i);
                   if (i > 0) {
                       result.append(",");
                   }

                   if (arrayMember instanceof JSONObject || arrayMember instanceof JSONArray) {
                       result.append(getSortJsonStr(arrayMember));
                   } else if (arrayMember instanceof String) {
                       result.append("\"" + arrayMember + "\"");
                   } else {
                       result.append(arrayMember);
                   }
               }
               result.append("]");
           } else if (objJson instanceof JSONObject) {
               result.append("{");
               Iterator itKeys = ((JSONObject) objJson).keys();
               TreeSet setKeys = new TreeSet();
               while (itKeys.hasNext()) {
                   setKeys.add(itKeys.next());
               }
               Iterator sortedIt = setKeys.iterator();
               for (Iterator it = sortedIt; sortedIt.hasNext(); ) {
                   String key = (String) it.next();
                   Object object = ((JSONObject) objJson).get(key);

                   if (object == null) {
                       continue;
                   }

                   if (result.length() > 1) {
                       result.append(",");
                   }

                   if (object instanceof String) {
                       result.append("\"" + key + "\":");
                       result.append("\"" + object + "\"");
                   } else if (object instanceof Long
                           || object instanceof Integer) {
                       result.append("\"" + key + "\":");
                       result.append(object);
                   } else if (object instanceof JSONObject
                           || object instanceof JSONArray) {
                       result.append("\"" + key + "\":");
                       result.append(getSortJsonStr(object));
                   }
               }
               result.append("}");
           }

       } catch (JSONException e) {
           e.printStackTrace();
       }

       return result.toString();
   }
}