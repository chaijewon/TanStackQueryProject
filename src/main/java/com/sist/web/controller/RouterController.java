package com.sist.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RouterController {
   private final FoodService fService;
   @GetMapping("/main")
   public String main_page(Model model) {
	   model.addAttribute("main_html", "main/home");
	   return "main/main";
   }
   @GetMapping("/food/list")
   public String food_list(
	   @RequestParam(value="search",required = false) String search,
	   @RequestParam(value ="page",required = false) String page,
	   Model model) {
	   
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   map.put("search", search);
	   map.put("start", (curpage*12)-12);
	   List<FoodVO> list=fService.foodListData(map);
	   int count=fService.foodListTotalPage(search);
	   // 페이지 나누기 
	   
	   
	   model.addAttribute("main_html", "food/list");
	   return "main/main";
   }
}
