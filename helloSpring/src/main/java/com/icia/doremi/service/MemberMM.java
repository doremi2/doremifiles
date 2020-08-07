package com.icia.doremi.service;

import org.springframework.web.servlet.ModelAndView;

public class MemberMM {

	public ModelAndView access() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg","login success");
		mav.setViewName("home");
		return mav;
	}

}
