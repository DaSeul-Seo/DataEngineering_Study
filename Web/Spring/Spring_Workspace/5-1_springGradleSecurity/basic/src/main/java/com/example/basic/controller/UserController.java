package com.example.basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.basic.model.dto.UserDto;
import com.example.basic.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    // 일반화면
    @GetMapping("/index")
    public String index() {
        log.info("[UserController][index] Get Start");
        return "index";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("[UserController][logout] Get Start");

        // 로그인 정보 제거 (세션 제거)
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        session.removeAttribute("userRole");

        return "redirect:/login";
    }

    // 관리자 화면
    @GetMapping("/admin")
    public String admin(HttpServletRequest request, Model model) {
        log.info("[UserController][admin] Get Start");

        HttpSession session = request.getSession();
        // 인증 확인 -> 로그인 유무 확인
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        // 인가 확인 -> 관리자 유무 확인
        else if (!session.getAttribute("userRole").equals("admin")) {
            return "redirect:/user";
        }

        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userRole", session.getAttribute("userRole"));

        return "admin";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginPage() {
        log.info("[UserController][login] Get Start");
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDto dto, HttpServletRequest request) {
        log.info("[UserController][login] Post Start");

        UserDto userInfo = userService.loginUser(dto);

        // DB에 user가 없는 경우
        if (userInfo == null) {
            log.info("[UserController][login] 회원이 아님");
            return "redirect:/join";
        }
        // 비밀번호가 틀릴 경우
        else if (userInfo.getUserEmail() == null) {
            log.info("[UserController][login] 비밀번호가 틀림");
            return "redirect:/login";
        }

        log.info("[UserController][login] 로그인 성공");
        // 로그인 정보 담기 (session)
        HttpSession session = request.getSession();
        // userId : 로그인 유무
        session.setAttribute("userId", userInfo.getUserId());
        // userRole : 사용자 권한 확인
        session.setAttribute("userRole", userInfo.getUserRole());

        return "redirect:/user";
    }

    // 회원가입 화면
    @GetMapping("/join")
    public String joinPage() {
        log.info("[UserController][join] Get Start");
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserDto dto) {
        log.info("[UserController][join] Post Start");

        userService.joinUser(dto);

        // 회원가입하면 로그인 화면으로 전환
        return "redirect:/login";
    }

    // 유저 화면
    @GetMapping("/user")
    public String user(HttpServletRequest request, Model model) {
        log.info("[UserController][user] Get Start");

        HttpSession session = request.getSession();
        // 인증 확인 -> 로그인 유무 확인
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userRole", session.getAttribute("userRole"));
        return "user";
    }
}
