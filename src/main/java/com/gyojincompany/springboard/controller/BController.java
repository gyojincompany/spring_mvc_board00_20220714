package com.gyojincompany.springboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.springboard.command.BCommand;
import com.gyojincompany.springboard.command.BWriteCommand;

@Controller
public class BController {
	
	BCommand command;
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		
		return "write_form";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
