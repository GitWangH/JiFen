package com.huatek.frame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huatek.frame.core.util.PropertyUtil;

@Controller
@RequestMapping("/")
public class IndexController {//Serves main index.html
	  	@RequestMapping(value = "")
	    public String getIndexPage(Model model) {
	  		//导入模版下载
	  		model.addAttribute("fileServerIp",PropertyUtil.getConfigValue("fileServerIp"));
	  		model.addAttribute("exportServerIp",PropertyUtil.getConfigValue("exportServerIp"));
	        return "index";
	    }
	    	
}
