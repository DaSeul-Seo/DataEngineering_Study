package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.basic.model.UserDto;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/thymeleaf")
public class BasicController {

    @GetMapping("/greeating")
    public String greeating(
        // 파라미터명은 name, 필수(required)는 아니고, 없으면 World
        @RequestParam(name = "name", required = false, defaultValue = "World")
        String name, Model model) {
        log.info("[BasicController][greeating] Start");
        log.info("name : " + name);
        
        // model = Data : Model을 가지고 화면을 만든다.
        // model 객체에 데이터를 key, value로 넣어준다.
        // addAttribute로 데이터를 추가한다. => 화면에 전달한다.
        // key에 name값을 넣는다. => .html에서 사용할 변수명 = key
        model.addAttribute("key", name); 
        return "greeting"; // html 파일명이랑 통일
    }

    @GetMapping("/utext")
    public String utext(Model model) {

        model.addAttribute("tag", "<h2>태그 전달</h2>");
        return "utext";
    }

    @GetMapping("/pv/{num}")
    public String pv1(Model model, @PathVariable int num) {
        model.addAttribute("num", num);
        return "thymeleaf/pv1";
    }

    // 메소드명이 다르면 url 주소 같아도 된다.
    // 최초화면 접근
    @GetMapping("/form")
    public String getForm(
                Model model, 
                @RequestParam(name = "name", required = false, defaultValue = "World") String data1) {
        
        model.addAttribute("data1", data1);
        return "thymeleaf/form";
    }

    // form 제출 누를 시 데이터 전달
    @PostMapping("/form")
    public String postForm(Model model, @RequestParam("data1") String data1) {

        model.addAttribute("data1", data1);
        return "thymeleaf/form";
    }

    @GetMapping("/mav")
    // 반환이 ModelAndView 이면 파라미터도 ModelAndView 받아야 한다.
    public ModelAndView modelAndView(ModelAndView modelAndView) {

        // 데이터 추가
        modelAndView.addObject("name", "seo");
        modelAndView.addObject("age", 22);

        // 화면 추가
        modelAndView.setViewName("thymeleaf/mav");

        return modelAndView;
    }

    @GetMapping("/multiForm")
    public ModelAndView multiForm(ModelAndView mav) {
        mav.addObject("msg", "여러 input 값 입력 후 전송 버튼 클릭");
        mav.setViewName("thymeleaf/multiForm");
        return mav;
    }

    @PostMapping("/multi")
    public ModelAndView multiSend(
        ModelAndView mav, 
        @ModelAttribute UserDto dto) {

        mav.addObject("msg", "dto를 사용하였습니다.");
        mav.addObject("userDto", dto);
        mav.setViewName("thymeleaf/multiSend");
        return mav;
    }
}
