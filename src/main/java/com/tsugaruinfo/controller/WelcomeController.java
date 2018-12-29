package com.tsugaruinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ウェルカムコントローラー
 * トップページを表示する
 * @author pratula
 *
 */
@Controller
public class WelcomeController {

	@RequestMapping(value = "/top", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("view/top");

		return mv;
	}
}