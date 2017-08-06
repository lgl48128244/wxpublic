package com.uflowertv.test;


import com.uflowertv.base.BaseFunction;


public class WeixinTest extends Thread{
	public static void main(String[] args) {
		try {
			//AccessToken token = BaseFunction.getAccessToken();
			//System.out.println(token.getToken());
			//String token = "0t06G1c5RBEPfHYXlJfeKbc3iMClTbfwoea8uHb2RYXI-ou-Je4RhBdysNeg-OJcGQjP_bjrQkQFJ1tFbGpimAmPRxZgduyUY4o7xnv4ObwCGHbAFARVW";
			//String path = "D:/picture/belle.jpg";
			//(path, token, "thumb")
			/*UploadParams params = new UploadParams();
			params.setAccess_token(token);
			params.setType(Type.image.name());
			params.setFile(new File(path));
			String mediaId = BaseFunction.upload(params);
			System.out.println(mediaId);*/
			
			//String menu = JSONObject.fromObject(BaseFunction.initMenu()).toString();
			//String result = BaseFunction.createMenu(token, menu);
			//String result = BaseFunction.deleteMenu(token);
			//Menu menu = BaseFunction.queryMenu(token);
			String result = BaseFunction.baidufanyi("谢谢你的爱");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
