package com.uflowertv.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private static final Log log = LogFactory.getLog(JsonUtils.class);
	
	private final static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * get Instance of ObjectMapper
	 * 
	 * @return ObjectMapper
	 */
	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * JavaBean convert to Json
	 * @param bean
	 * @return
	 */
	public static String bean2Json(Object bean) {
		try {
			objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			return objectMapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			log.error("jackson进程异常:" + e.getMessage());
			return null;
		}
	}
	/**
	 * 
	 * @param bean
	 * @param flag 是否剔除空
	 * @return
	 */
	public static String bean2Json(Object bean,boolean flag) {
		String json = null;
		try {
			if(flag){
				objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			}
			else
			{
				ObjectMapper objectMapperTemp = new ObjectMapper();
				json = objectMapperTemp.writeValueAsString(bean);
			}
		} catch (JsonProcessingException e) {
			log.error("jackson进程异常:" + e.getMessage());
			return null;
		}
		return json;
	}

	/**
	 * Json convert to JavaBean
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T json2Bean(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (JsonParseException e) {
			log.error("Jackson解析异常:" + e.getMessage());
			return null;
		} catch (JsonMappingException e) {
			log.error("Jackson映射异常:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Json convert to Map
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> json2Map(String json) {
		try {
			return objectMapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			log.error("Jackson解析异常:" + e.getMessage());
			return null;
		} catch (JsonMappingException e) {
			log.error("Jackson映射异常:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Json convert to Map with javaBean
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> Map<String, T> json2Map(String json, Class<T> clazz) {
		Map<String, Map<String, Object>> map;
		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String, T>>(){});
			Map<String, T> result = new HashMap<String, T>();
			for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
				result.put(entry.getKey(), map2Bean(entry.getValue(), clazz));
			}
			return result;
		} catch (JsonParseException e) {
			log.error("Jackson解析异常:" + e.getMessage());
			return null;
		} catch (JsonMappingException e) {
			log.error("Jackson映射异常:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Json convert to List with JavaBean
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> json2List(String json, Class<T> clazz) {
		List<Map<String, Object>> list;
		try {
			list = objectMapper.readValue(json, new TypeReference<List<T>>() {});
			List<T> result = new ArrayList<T>();
			for (Map<String, Object> map : list) {
				result.add(map2Bean(map, clazz));
			}
			return result;
		} catch (JsonParseException e) {
			log.error("Jackson解析异常:" + e.getMessage());
			return null;
		} catch (JsonMappingException e) {
			log.error("Jackson映射异常:" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.error("IO异常:" + e.getMessage());
			return null;
		}
	}

	/**
	 * Map convert to JavaBean
	 * 
	 * @param map
	 * @param clazz
	 * @return bean
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T map2Bean(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}

}
