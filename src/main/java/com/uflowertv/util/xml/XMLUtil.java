package com.uflowertv.util.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.ProcessingInstruction;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：
 * 类名称：com.uflowertv.xml.XMLUtil     
 * 创建人：liguoliang 
 * 创建时间：2016年8月11日 下午6:04:49   
 * 修改人：
 * 修改时间：2016年8月11日 下午6:04:49   
 * 修改备注：   
 * @version   V1.0
 */
public class XMLUtil {
	
	private static final Log logger = LogFactory.getLog(XMLUtil.class);
	/**

	 * 以编码为utf-8的方式返回xml
	 * 
	 * @param doc
	 *            Document
	 * @return String
	 */
	public static String toString(Document doc) {
		return toString(doc, "utf-8");
	}

	/**

	 * 以指定编码格式返回xml
	 * 
	 * @param doc
	 *            Document
	 * @param encoding
	 *            String
	 * @return String
	 */
	public static String toString(Document doc, String encoding) {
		if (null != doc) {
			OutputFormat outputFormat = new OutputFormat();
			outputFormat.setEncoding(encoding);
			StringWriter stringWriter = new StringWriter();
			XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
			try {
				xmlWriter.write(doc);
				return stringWriter.toString();
			} catch (IOException ex) {
				ex.printStackTrace();
				return "";
			}
		} else {
			return "";
		}
	}
	
	public static Document strToDoc(String strXml) {
		try {
			Document doc = DocumentHelper.parseText(strXml);
			return doc;
		} catch (Exception e) {
			logger.error("解析xml文件出现错误",e);
			logger.error("将字符串转换为Doc时出现错误，要转换的字符串为："+strXml);
			return null;
		}
	}
	
	public static Document loadXML(String path) {
		File f = new File(path);    
		SAXReader reader = new SAXReader();    
		try {
			return reader.read(f);
		} catch (Exception e) {

			logger.error("加载DOM文件异常，文件路径为：" + path);
			return null;
		} 
	}
	
	public static Document transforXml(String content) throws Exception{
		SAXReader saxReader = new SAXReader();
//		saxReader.setEncoding("UTF-8");
		InputStream in = new ByteArrayInputStream(content.getBytes("UTF-8")); 
		InputStreamReader utf8In=new InputStreamReader(in,"UTF-8"); 
//		InputStreamReader utf8In=new InputStreamReader(in,"GBK");
		return saxReader.read(utf8In); 
	}
	
	public static Document transforXml(String content,String encode) throws Exception{
		SAXReader saxReader = new SAXReader();
//		saxReader.setEncoding("UTF-8");
		InputStream in = new ByteArrayInputStream(content.getBytes()); 
		InputStreamReader utf8In=new InputStreamReader(in,encode); 
//		InputStreamReader utf8In=new InputStreamReader(in,"GBK");
		return saxReader.read(utf8In); 
	}


	public static void saveXMLDocumentToFile(Document doc, String encoding,
			String strFileName) throws IOException {

		// 创建路径

		String str = strFileName.replaceAll("\\\\", "/");
		int p = str.lastIndexOf("/");
		if (p > 0) {
			String strDir = str.substring(0, p);
			DirectoryUtil.forceDirectory(strDir);
		}

		if (encoding == null || encoding.length() == 0) {
			encoding = "UTF-8";
		}
		OutputFormat outputFormat = new OutputFormat();
		outputFormat.setEncoding(encoding);
		FileOutputStream filOutput = new FileOutputStream(strFileName);
		XMLWriter xmlWriter = new XMLWriter(filOutput, outputFormat);
		xmlWriter.write(doc);
		xmlWriter.close();
		filOutput.close();
	}

	public static void saveXMLDocumentToOutputStream(Document doc,
			String encoding, OutputStream outputstream) throws IOException {
		if (encoding == null || encoding.length() == 0) {
			encoding = "UTF-8";
		}
		OutputFormat outputFormat = new OutputFormat();
		outputFormat.setEncoding(encoding);
		XMLWriter xmlWriter = new XMLWriter(outputstream, outputFormat);
		xmlWriter.write(doc);
		xmlWriter.close();
		outputstream.close();
	}

	public static Document loadXMLFile(String strFileName)
			throws DocumentException {
		SAXReader saxReader = new SAXReader();
		return saxReader.read(new File(strFileName));
	}

	public static Document loadXMLInputstream(InputStream in) {
		SAXReader reader = new SAXReader();
		try {
			return reader.read(in);
		} catch (DocumentException e) {

			logger.error("解析xml文件出现错误");
			try {
				logger.error("将字符串转换为Doc时出现错误，要转换的字符串为："+StreamUtil.toString(in,"UTF-8"));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			logger.debug("未能进行转化",e);

			return null;
		}
	}

	/**

	 * 用于xml 与 xsl 的归并输出含处理指令的xml到out 处理指令指定了浏览器渲染的时候使用的xsl文件相对路径
	 * 
	 * @author sun
	 */
	@SuppressWarnings("unchecked")
	static public void outputXML(Document xmldoc, String xslname, Writer out)
			throws Exception {
		if (xslname != null) {
			ProcessingInstruction pi = DocumentHelper
					.createProcessingInstruction("xml-stylesheet", "href=\""
							+ xslname + "\" type=\"text/xsl\"");
			xmldoc.content().add(0, pi);
		}
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		transformer.setOutputProperty("encoding", "GB2312");

		transformer
				.transform(new DocumentSource(xmldoc), new StreamResult(out));
		out.flush();
		out.close();
	}

	/**
	 * 用于xml 与 xsl 的归并输出xml或html到out 输出html时，xsl名称不能为null
	 * 
	 * @author
	 * @throws TransformerException
	 */
	static public void transformXml(Document xmldoc, String xslname, Writer out)
			throws TransformerException {
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = xslname == null ? factory.newTransformer()
				: factory.newTransformer(new StreamSource(xslname));
		transformer.setOutputProperty("encoding", "GB2312");

		transformer
				.transform(new DocumentSource(xmldoc), new StreamResult(out));
	}
	
	public static void main(String[] args) {
		try {
			Document d = XMLUtil.loadXMLFile("D:\\test.xml");
			XMLUtil.transforXml(d.asXML());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}