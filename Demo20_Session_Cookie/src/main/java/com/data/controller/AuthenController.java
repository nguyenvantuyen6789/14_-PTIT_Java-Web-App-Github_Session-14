package com.data.controller;

import com.data.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenController {

    @Autowired
    private ServletContext context;

    private Account account = new Account("t1", "123", "Nguyen Ngoc");

    @GetMapping("login")
    public String goLoginForm(@CookieValue(value = "username", defaultValue = "") String username,
                              @CookieValue(value = "password", defaultValue = "") String password,
                              Model model) {
        int cookieTimeout = 0;
        try {
            cookieTimeout = Integer.parseInt(context.getInitParameter("cookieTimeout"));
        } catch (NumberFormatException e) {
            System.out.println("CookieTimeout is not a number. Set CookieTimeout = 0");
        }

        model.addAttribute("usernameCookie", username);
        model.addAttribute("passwordCookie", password);

        return "login";
    }

    @PostMapping("login")
    public String loginAccount(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               HttpSession session,
                               HttpServletResponse response) {
        if (username.equals(account.getUsername()) && password.equals(account.getPassword())) {
            session.setAttribute("username", username);
            session.setAttribute("fullName", account.getFullName());
            session.setAttribute("isLogin", true);

            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(1 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);

            Cookie cookie2 = new Cookie("password", password);
            cookie2.setMaxAge(1 * 60);
            cookie2.setPath("/");
            response.addCookie(cookie2);

            return "redirect:/home";
        }
        return "login";
    }
    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("fullName");
        session.removeAttribute("isLogin");
        return "redirect:/login";
    }
}
