package com.uflowertv.commons;

import org.springframework.core.convert.converter.Converter;


/**
 * 去掉前后空格 
 * 传入的String
 * 转完之后String  Date
 * @author lx
 *
 */
public class CustomTrimConverter implements Converter<String, String>{

	@Override
	public String convert(String source) {
		// TODO Auto-generated method stub
		try {
			if(null != source){
				source = source.trim();// ""
				if("".equals(source)){
					return null;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return source;
	}

}
