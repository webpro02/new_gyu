package com.spring.oauth2.controller;

import com.spring.oauth2.model.UserResource;
import com.spring.oauth2.service.AccessTokenRequestService;
import com.spring.oauth2.service.UserInfoRequestService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	UserResource ur = new UserResource();
	AccessTokenRequestService tr = new AccessTokenRequestService();
	UserInfoRequestService ui = new UserInfoRequestService();
	
	@GetMapping({"", "/"})
	public String IndexPage() {
		return "index";
	}
	@GetMapping("/user/facebook")
	public String UserPage(HttpServletRequest request, Model model) {
		// code 저장
		ur.SetCode(request.getParameter("code"));
		
		// access token 저장
		String at = tr.AccessToken(ur.GetCode());
		ur.SetAccessToken(at);
		
		// name 저장
		String nm = ui.Name(at);
		ur.SetName(nm);
		
		// email 저장
		String em = ui.Email(at);
		ur.SetEmail(em);
		
		model.addAttribute("accesstoken", ur.GetAccessToken());
		model.addAttribute("name", ur.GetName());
		model.addAttribute("email", ur.GetEmail());
		
		System.out.println("code = " + ur.GetCode());
		System.out.println("AccessToken = " + (String)model.getAttribute("accesstoken"));
		System.out.println("Name = " + (String)model.getAttribute("name"));
		System.out.println("Email = " + (String)model.getAttribute("email"));
		
		return "user";
	}
	@GetMapping("/logout")
    public String logout(HttpServletRequest request) {
		System.out.println("logout on");
		
		Cookie[] cookie = request.getCookies();
        HttpSession session = request.getSession(false);
        if (cookie != null) {
        	System.out.println("cookie is true");
        }
        if (session != null) {
        	System.out.println("session is true");
            session.invalidate();
        }
        return "index";
    }
}
