///*
// *--------------------------------------------------------
// * Administrateur
// *--------------------------------------------------------
// * Project     : freeMarker
// *
// * Copyright Administrateur,  All Rights Reserved.
// *
// * This software is the confidential and proprietary
// * information of Administrateur.
// * You shall not disclose such Confidential Information
// * and shall use it only in accordance with the terms
// * of the license agreement you entered into with
// * Administrateur.
// *-------------------------------------------------------- 
// * 
// * Fichier 		:	CreateHtmlTest.java
// * Cree le 		: 	6 d閏. 2015 ?00:44:35
// * Auteur		: 	admin
// * 
// * Description 	:
// * 
// *---------------------------------------------------------
// */
//
//package com.uflowertv.test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.uflowertv.model.NoticeWithBLOBs;
//import com.uflowertv.template.TemplateModel;
//import com.uflowertv.template.tools.Tools;
//import com.uflowertv.util.SpringContextHolder;
//
///**
// * A Renseigner.
// * @author  : admin
// * @project : freeMarker
// * @package : com.template
// * @date    : 6 d閏. 2015 02:44:35
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(locations= {"classpath*:applicationContext.xml","classpath*:springmvc-servlet.xml"})
//public class TemplateModelTest
//{
//   private static final Logger logger = org.slf4j.LoggerFactory.getLogger( TemplateModelTest.class );
//   
//  // @Autowired
//  // private TemplateModel  templateModel;
//   
//   
//  ClassPathXmlApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
//  TemplateModel  templateModel =  (TemplateModel) cxt.getBean("templateModel");
//   
//   @Test
//   public void createHTMLTest()
//   {
//      logger.info( "createHTMLTest input" );
//      /** Create a data model */
//      Map<String, Object> mapTemplateData = new HashMap<String, Object>();
//      NoticeWithBLOBs noticeWithBLOBs = new NoticeWithBLOBs();
//      noticeWithBLOBs.setWxUserQuestion("323232");
//      noticeWithBLOBs.setReplyQuestion("fgfgfgf");
//      mapTemplateData.put( "notice", noticeWithBLOBs);
//	  mapTemplateData.put( "replyTime", Tools.getDateString( new Date(), "yyyy-MM-dd HH:mm:ss" ) );
//	  String htmlName = String.valueOf(new Date().getTime());
//	  templateModel.createHtml( mapTemplateData, "user_question.ftl", "html/user_question/"+htmlName+".html", "/WEB-INF/ftl/", "2.3.23" );
//      logger.info( "createHTMLTest output" );
//   }
//   
//   
//   @After
//   public void destroy()
//   {
//      logger.info( "destroy" );
//      templateModel = null;
//      assertNull( templateModel );
//   }
//}