package com.uflowertv.model;


public class TreeData {
	//必须属性，从DAO获取。
	private String id;
	private String text;
	//非必须属性，取默认值。
	private String state="closed";//根节点使用默认值，closed。末级节点把state赋值成open
	private TreeAttributes attributes = new TreeAttributes();//有url地址的节点时候，从DAO取出url赋值给TreeAttributes的URL属性。
	private String children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public TreeAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(TreeAttributes attributes) {
		this.attributes = attributes;
	}
	public String getChildren() {
		return children;
	}
	public void setChildren(String children) {
		this.children = children;
	}
	
}
