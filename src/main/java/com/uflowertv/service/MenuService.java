package com.uflowertv.service;

import io.github.elkan1788.mpsdk4j.vo.api.Menu;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uflowertv.commons.BaseService;
import com.uflowertv.dao.ConfigurationMapper;
import com.uflowertv.model.Configuration;
import com.uflowertv.util.xml.XMLUtil;
/**
 * 
 * 版权所有：2016-油菜花
 * 项目名称：Weixin   
 *
 * 类描述：微信菜单业务功能
 * 类名称：com.uflowertv.service.MenuService     
 * 创建人：liguoliang 
 * 创建时间：2016年8月15日 下午12:15:49   
 * 修改人：
 * 修改时间：2016年8月15日 下午12:15:49   
 * 修改备注：   
 * @version   V1.0
 */
@Service
public class MenuService extends BaseService{

	@Autowired
	private ConfigurationMapper configurationMapper;
	/**
	 * 更新菜单
	 * @Title: createMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Menu[] createMenu(){
		log.info("更新微信菜单");
		//获取菜单信息
		Configuration configuration = configurationMapper.selectByPrimaryKey("menu");
		
		Document document = XMLUtil.strToDoc(configuration.getValue());
		Element root = document.getRootElement();
		
		//一级菜单
		Element oneMenuElement = (Element) root.selectSingleNode("//onemenu");
		String oneMenuName = oneMenuElement.attributeValue("name");
		List<Element> subOneMenuElement = oneMenuElement.elements();
		
		List<Menu> oneMenuList = new ArrayList<Menu>();
		for (Element element : subOneMenuElement) {
			String name = element.attributeValue("name");
			String type = element.attributeValue("type");
			String key = element.attributeValue("key");
			Menu subOneMenu = new Menu(name,type,key);
			oneMenuList.add(subOneMenu);
		}
		
		Menu oneMenu = new Menu();
		oneMenu.setName(oneMenuName);
		oneMenu.setSubButtons(oneMenuList);
		
		//二级菜单
		Element twoMenuElement = (Element) root.selectSingleNode("//twomenu");
		String twoMenuName = twoMenuElement.attributeValue("name");
		List<Element> subTwoMenuElement = twoMenuElement.elements();
		List<Menu> twoMenuList = new ArrayList<Menu>();
		for (Element element : subTwoMenuElement) {
			String name = element.attributeValue("name");
			String type = element.attributeValue("type");
			String key = element.attributeValue("key");
			Menu subTwoMenu = new Menu(name,type,key);
			twoMenuList.add(subTwoMenu);
		}
		
		Menu twoMenu = new Menu();
		twoMenu.setName(twoMenuName);
		twoMenu.setSubButtons(twoMenuList);
		
		
		//三级菜单
		Element threeMenuElement = (Element) root.selectSingleNode("//threemenu");
		String threeMenuName = threeMenuElement.attributeValue("name");
		List<Element> subThreeMenuElement = threeMenuElement.elements();
		List<Menu> threeMenuList = new ArrayList<Menu>();
		for (Element element : subThreeMenuElement) {
			String name = element.attributeValue("name");
			String type = element.attributeValue("type");
			String key = element.attributeValue("key");
			Menu subThreeMenu = new Menu(name,type,key);
			threeMenuList.add(subThreeMenu);
		}
		
		Menu threeMenu = new Menu();
		threeMenu.setName(threeMenuName);
		threeMenu.setSubButtons(threeMenuList);
		
		Menu[] customerMenus = {oneMenu,twoMenu,threeMenu};
		return customerMenus;
	}
}
