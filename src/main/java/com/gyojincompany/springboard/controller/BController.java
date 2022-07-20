package com.gyojincompany.springboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.springboard.command.BCommand;
import com.gyojincompany.springboard.command.BContentCommand;
import com.gyojincompany.springboard.command.BDeleteCommand;
import com.gyojincompany.springboard.command.BListCommand;
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
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		
		command = new BListCommand();
		command.execute(model);//model 에 list(ArrayList<BDto> dtos)가 탑재
		
		return "list";
	}
	
	@RequestMapping(value = "/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
