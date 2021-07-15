package jp.ac.hcs.s3a216.main;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PortalController {
	
	

	
	@RequestMapping("/")
	public String index(Principal principal,Model model) {
	
		
		return "index";
	}

}
