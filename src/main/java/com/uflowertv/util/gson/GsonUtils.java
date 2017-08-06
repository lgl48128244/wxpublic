package com.uflowertv.util.gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Utilities to serialize/deserialize objects using Gson library.
 *
 * @author: Lucy Linder
 * @date: 23.05.2013
 * 
 * Gson类的使用： 
 * 是 Google 提供的用来在 Java 对象和 JSON 数据之间进行映射的 Java 类库。可以将一个 JSON 字符串转成一个 Java 对象，或者反过来。
 * 
 * **************************************************************************************
 * 
 * GsonBuilder类的使用：
 * 有时候我们不需要把实体的所有属性都导出,只想把一部分属性导出为Json.
 * 有时候我们的实体类会随着版本的升级而修改.
 * 有时候我们想对输出的json默认排好格式.
 * 那么GsonBuilder是一个不错的选择
 * 
 * **************************************************************************************
 * 
 * TypeToken类的使用：
 * Map的存储结构式Key/Value形式,Key 和 Value可以是普通类型,也可以是自己写的JavaBean(本文),还可以是带有泛型的List.
 * 如何将Json转回为普通JavaBean对象时TypeToken的定义
 *     Map<String,String> map = new HashMap<String,String>();
 *     map.put(...);
 *     String json = GsonUtils.toJson(map);
 *     Map<String, String> deserialisedMap = 
 *     (Map<String,String>)GsonUtils.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
 * 	   for (Map.Entry<String, String> map : deserialisedMap.entrySet()) {
 *			key = map3.getKey();
 *			value = map3.getValue();
 *		}
 *
 *     Map<String,Object> map = new HashMap<String,Object>();
 *     map.put(...);
 *     String json = GsonUtils.toJson(map);
 *       for (String key : map.keySet()) {  
 *           System.out.println("key:" + key + " values:" + retMap.get(key));  
 *           if (key.equals("students")) {  
 *               List<Student> stuList = (List<Student>) retMap.get(key);  
 *               System.out.println(stuList);  
 *           } else if (key.equals("teachers")) {  
 *               List<Teacher> tchrList = (List<Teacher>) retMap.get(key);  
 *               System.out.println(tchrList);  
 *           }  
 *       }  
 *
 *
 */
public class GsonUtils {

	private static Logger log = LoggerFactory.getLogger(GsonUtils.class);

	/***************************************Gson类*******************************************/
	/**
	 * 将map转换为json字符串
	 * @param source
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final String mapToJson(Map source) {
		return new Gson().toJson(source);
	}

	/**
	 * 将json字符串转换为map数据
	 * @param source
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final Map jsonToMap(String source) {
		return new Gson().fromJson(source, HashMap.class);
	}

	/**
	 * 将对象转换成json
	 * @param object 
	 * @param c
	 * @return
	 */
	public static final String objToJson(Object object) {
		return new Gson().toJson(object);
	}

	/**
	 * 将json字符串转换成对象
	 * @param jsonData
	 * @param c
	 * @return
	 */
	public static final <T> T jsonToObj(String jsonData, Type type) {
		return new Gson().fromJson(jsonData, type);
	}

	/***************************************GsonBuilder类（指定对象属性转换成JSON）*******************************************/

	/**
	 * 通过URL获取JSON对象
	 */

	/** see {@link GsonUtils#getJsonFromFile(java.io.InputStream, Object)} */
	public static Object getJsonFromFile(String path, Object container) {
		return getJsonFromFile(new File(path), container);
	}// end getJsonFromFile

	/**
	 * 通过文件获取JSON对象
	 */

	/** see {@link GsonUtils#getJsonFromFile(java.io.InputStream, Object)} */
	public static Object getJsonFromFile(File file, Object container) {

		InputStream fin = null;
		try {
			fin = new FileInputStream(file);
			return getJsonFromFile(fin, container);
		} catch (Exception e) {
			log.error(e.getMessage() + "exception while getting json from file " + file.getPath() + " " + "catched.");
		}
		return null;
	}// end getJsonFromFile

	/**
	 * 通过流的方式获取JSON对象
	 */

	/**
	 * read a json stream and store its content into the fields of
	 * the container object. <br />
	 * The json entries must match the container fields. If a field of the container does not
	 * appear in the json file (or its value is left empty), the field is set to null.
	 * non-existent json entries ,
	 *
	 * @param stream the json stream to read from
	 */
	public static Object getJsonFromFile(InputStream stream, Object container) {

		try {
			return new GsonBuilder().create() //
					.fromJson(new InputStreamReader(stream), container.getClass());

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage());
			}
		}
		return null;

	}// end getJsonFromFile

	// ----------------------------------------------------

	/**
	 * 通过URL方式JSON对象 TypeToken:TypeToken<List<String>> list = new TypeToken<List<String>>() {};
	 */

	/** see {@link GsonUtils#getJsonFromFile(java.io.InputStream, com.google.gson.reflect.TypeToken)} */
	@SuppressWarnings("rawtypes")
	public static Object getJsonFromFile(String path, TypeToken typeToken) {
		return getJsonFromFile(new File(path), typeToken);
	}// end getJsonFromFile

	/**
	 * 通过文件方式JSON对象 TypeToken:TypeToken<List<String>> list = new TypeToken<List<String>>() {};
	 */

	/** see {@link GsonUtils#getJsonFromFile(java.io.InputStream, com.google.gson.reflect.TypeToken)} */
	@SuppressWarnings("rawtypes")
	public static Object getJsonFromFile(File file, TypeToken typeToken) {

		InputStream fin = null;
		try {
			fin = new FileInputStream(file);
			return getJsonFromFile(fin, typeToken);
		} catch (Exception e) {
			log.error(e.getMessage() + "exception while getting json from file " + file.getPath() + " " + "catched.");
		}
		return null;
	}// end getJsonFromFile

	/**
	 * 通过流方式JSON对象 TypeToken:TypeToken<List<String>> list = new TypeToken<List<String>>() {};
	 */

	/**
	 * read a json stream and store its content into a generic object.
	 *
	 * @param stream    the json stream to read from
	 * @param typeToken the typetoken of the object to return
	 * @return the object, or null if an error occurred
	 */
	@SuppressWarnings("rawtypes")
	public static Object getJsonFromFile(InputStream stream, TypeToken typeToken) {

		try {
			return new GsonBuilder().create() //
					.fromJson(new InputStreamReader(stream), typeToken.getType());

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				//
				log.error(e.getMessage());
			}
		}
		return null;

	}// end getJsonFromFile

	// ----------------------------------------------------

	/**
	 * 通过文件方式将对象写入到JSON格式文件中,prettyPrinting:是否精美打印
	 */

	/**
	 * Serialize the given object into json
	 *
	 * @param file           the destination file
	 * @param container      the object
	 * @param prettyPrinting true if the json should be written with spaces and formatting, false
	 *                       for a compressed output
	 * @return true if the operation could be performed, false otherwise
	 */
	public static boolean writeJsonFile(File file, Object container, boolean prettyPrinting) {
		try {
			OutputStream fos;
			GsonBuilder builder = new GsonBuilder();
			if (prettyPrinting)
				builder.setPrettyPrinting();

			String str = builder.setExclusionStrategies(new GsonSimpleExclusionStrategy()) //
					.create() //
					.toJson(container, container.getClass());

			fos = new FileOutputStream(file);
			fos.write(str.getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return false;
	}//end writeJsonFile

	/**
	 * 通过URL方式将对象写入到JSON格式文件中,prettyPrinting:是否精美打印
	 */

	/** see {@link GsonUtils#writeJsonFile(String, Object, boolean)} */
	public static boolean writeJsonFile(String filepath, Object container, boolean prettyPrinting) {
		return writeJsonFile(new File(filepath), container, prettyPrinting);
	}//end writeJsonFile

	// ----------------------------------------------------
	/**
	 * 将对象转换成JSON字符串（不带注解）
	 */

	/**
	 * Serialize the given object into a json string, excluding no field.
	 *
	 * @param o the object
	 * @return the json string
	 */
	public static String dump(Object o) {
		return new GsonBuilder().setPrettyPrinting() //
				//.setExclusionStrategies( new GsonSimpleExclusionStrategy() ) //
				.create() //
				.toJson(o, o.getClass());
	}//end toJsonString

	/**
	 * 将对象转换成JSON字符串（带注解）
	 */

	/**
	 * Serialize the object into a json string, excluding fields annotated with {@link DoNotSerialize}.
	 *
	 * @param o the object
	 * @return the json string
	 */
	public static String toJson(Object o) {
		return new GsonBuilder().setPrettyPrinting() //
				.setExclusionStrategies(new GsonSimpleExclusionStrategy()) //
				.create() //
				.toJson(o, o.getClass());
	}//end toJsonString

	// ----------------------------------------------------

	/**
	 * 将JSON字符串转换成对象
	 */

	/**
	 * Deserialise a json string to an object.
	 * Example:
	 * <p/>
	 * <pre>
	 * {@code
	 *
	 * Map<String,String> map = new HashMap<>();
	 * map.put(...);
	 * String json = GsonUtils.toJson(map);
	 * Map<String, String> deserialisedMap = (Map<String,String>)
	 *      GsonUtils.fromJson(json, new TypeToken<Map<String,String>>(){}.getType());
	 * }
	 * </pre>
	 *
	 * @param json the json string
	 * @param type the object's type
	 * @return the object
	 */
	public static Object fromJson(String json, Type type) {
		return new GsonBuilder().create().fromJson(json, type);
	}

}// end class
