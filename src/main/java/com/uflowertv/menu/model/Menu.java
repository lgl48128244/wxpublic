package com.uflowertv.menu.model;

import com.uflowertv.bean.ResponseError;

public class Menu extends ResponseError{
	//一级菜单
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
}
