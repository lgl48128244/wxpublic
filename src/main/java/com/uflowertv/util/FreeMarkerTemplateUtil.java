package com.uflowertv.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FreeMarkerTemplateUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerTemplateUtil.class);
	
	private static Configuration freemarker_cfg = null;
	
	/**
	 * 获取freemarker的配置. freemarker本身支持classpath,目录和从ServletContext获取.
	 */
	protected static Configuration getFreeMarkerCFG(HttpServletRequest req) {
		if (null == freemarker_cfg) {
			File templateFile = new File(req.getServletContext().getRealPath("/") + "WEB-INF/ftl");
			 Version version = new Version("2.3.23", true, new Date());
			freemarker_cfg = new Configuration(version);
			try {
				freemarker_cfg.setDirectoryForTemplateLoading(templateFile);
				freemarker_cfg.setObjectWrapper( new DefaultObjectWrapper( version ) );
				freemarker_cfg.setEncoding(Locale.CHINA, "UTF-8");
			} catch (IOException e) {
				logger.error("Get tempalte error", e);
			}
		}
		return freemarker_cfg;
	}

	/**
	 * 生成静态文件.
	 * 
	 * @param templateFileName
	 *            模板文件名,相对htmlskin路径,例如"/tpxw/view.ftl"
	 * @param propMap
	 *            用于处理模板的属性Object映射
	 * @param htmlFilePath
	 *            要生成的静态文件的路径,相对设置中的根路径,例如 "/tpxw/1/2005/4/"
	 * @param htmlFileName
	 *            要生成的文件名,例如 "1.htm"
	 */
	public static boolean geneHtmlFile(
			HttpServletRequest req,
			Map<String, Object> propMap, 
			String htmlFilePath,
			String templateFileName,
			String htmlFileName) {
		// @todo 从配置中取得要静态文件存放的根路径:需要改为自己的属性类调用
		String sRootDir = req.getServletContext().getRealPath("/html");
		try {
			Template t = getFreeMarkerCFG(req).getTemplate(templateFileName);
			// 如果根路径存在,则递归创建子目录
			creatDirs(sRootDir, htmlFilePath);
			File afile = new File(htmlFilePath + "/" + htmlFileName);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile), "UTF-8"));
			t.process(propMap, out);
			out.flush();
			out.close();
		} catch (TemplateException e) {
			logger.error("Error while processing FreeMarker template "+ templateFileName, e);
			return false;
		} catch (IOException e) {
			logger.error("Error while generate Static Html File "+ htmlFileName, e);
			return false;
		}
		return true;
	}

	/**
	 * 创建多级目录
	 * 
	 * @param aParentDir
	 *            String
	 * @param aSubDir
	 *            以 / 开头
	 * @return boolean 是否成功
	 */
	public static boolean creatDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (!aFile.exists()) {
			aFile.mkdirs();
		}
		File aSubFile = new File(aParentDir + aSubDir);
		if (!aSubFile.exists()) {
			return aSubFile.mkdirs();
		} else {
			return true;
		}
	}
}
