package com.uflowertv.controller;

import io.github.elkan1788.mpsdk4j.core.WechatHandler;
import io.github.elkan1788.mpsdk4j.vo.event.BasicEvent;
import io.github.elkan1788.mpsdk4j.vo.event.CustomServiceEvent;
import io.github.elkan1788.mpsdk4j.vo.event.LocationEvent;
import io.github.elkan1788.mpsdk4j.vo.event.MenuEvent;
import io.github.elkan1788.mpsdk4j.vo.event.ScanCodeEvent;
import io.github.elkan1788.mpsdk4j.vo.event.ScanEvent;
import io.github.elkan1788.mpsdk4j.vo.event.SendLocationInfoEvent;
import io.github.elkan1788.mpsdk4j.vo.event.SendPhotosEvent;
import io.github.elkan1788.mpsdk4j.vo.message.BasicMsg;
import io.github.elkan1788.mpsdk4j.vo.message.ImageMsg;
import io.github.elkan1788.mpsdk4j.vo.message.LinkMsg;
import io.github.elkan1788.mpsdk4j.vo.message.LocationMsg;
import io.github.elkan1788.mpsdk4j.vo.message.TextMsg;
import io.github.elkan1788.mpsdk4j.vo.message.VideoMsg;
import io.github.elkan1788.mpsdk4j.vo.message.VoiceMsg;
import io.github.elkan1788.mpsdk4j.vo.push.SentAllJobEvent;
import io.github.elkan1788.mpsdk4j.vo.push.SentTmlJobEvent;

public class NoticeHandler implements WechatHandler{

	@Override
	public BasicMsg defMsg(BasicMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg defEvent(BasicEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg text(TextMsg msg) {
		msg.setContent("你的问题已经回复");
		return msg;
	}

	@Override
	public BasicMsg image(ImageMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg voice(VoiceMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg video(VideoMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg shortVideo(VideoMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg location(LocationMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg link(LinkMsg msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eClick(MenuEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eView(MenuEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BasicMsg eSub(BasicEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eUnSub(BasicEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BasicMsg eScan(ScanEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eLocation(LocationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BasicMsg eScanCodePush(ScanCodeEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eScanCodeWait(ScanCodeEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicSysPhoto(SendPhotosEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicPhotoOrAlbum(SendPhotosEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg ePicWeixin(SendPhotosEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicMsg eLocationSelect(SendLocationInfoEvent event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eSentTmplJobFinish(SentTmlJobEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eSentAllJobFinish(SentAllJobEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eCreateKfSession(CustomServiceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eCloseKfSession(CustomServiceEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eSwitchKfSession(CustomServiceEvent event) {
		// TODO Auto-generated method stub
		
	}

}
