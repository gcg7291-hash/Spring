package com.example.firstapp.controller;

import org.springframework.boot.jackson.JsonMixinModule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {


    @GetMapping("/") //localhost8080
    public String home(){
        return "home";
    }

    @GetMapping("/hello")
    public String hello(Model model){
        String name = "changgeun";
        model.addAttribute("name", name);

        return "hello";
    }

    @GetMapping("/user")
    public String user(Model model){
        model.addAttribute("username", "KIM");
        model.addAttribute("age", 20);
        model.addAttribute("city", "seoul");
        return "user";
    }

    @GetMapping("/fruits")
    public String fruits(Model model){
        List<String> fruitList = new ArrayList<>();
        fruitList.add("apple");
        fruitList.add("banana");
        fruitList.add("cherry");
        fruitList.add("lemon");
        fruitList.add("kiwi");
        fruitList.add("mango");

        model.addAttribute("fruits", fruitList);
        return "fruits";
    }

    @GetMapping("/grade")
    public String grade(Model model){
        int score = 100;
        model.addAttribute("score", score);
        return "grade";
    }

    @GetMapping("/lunch")
    public String lunch(Model model){
        List<String> menus = Arrays.asList("김밥", "라면", "돈까스");
//
        Random random = new Random();
        String pick = menus.get(random.nextInt(menus.size()));

        model.addAttribute("pick", pick);
        return "lunch";
    }

    @GetMapping("/lotto")
    public String lotto(Model model){
        List<Integer> numbers = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(numbers);

        List<Integer> luck = numbers.subList(0, 6);

        Collections.sort(luck);
        model.addAttribute("luck", luck);
        return "lotto";
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model){
        model.addAttribute("username", username);
        return "profile";
    }

    @GetMapping("/cube/{number}")
    public String cube(@PathVariable int number, Model model){
        int result = number * number * number;
        model.addAttribute("number", number);
        model.addAttribute("result", result);

        return "cube";
    }

    // 짝수 홀수 판별
    // /number/{num} => 짝수인지 홀수인지 판별해서 화면에 출력
    @GetMapping("/number/{num}")
    public String number(@PathVariable int num, Model model){
        // controller에서 판단
        String result = "";
        if(num % 2 == 0){
            result = "짝수 입니다.";
        } else {
            result = "홀수 입니다.";
        }

        model.addAttribute("num", num);
        model.addAttribute("result", result);


        // view에서 판단
//        model.addAttribute("num", num);

        return "number";
    }


    // 나이 계산 (year클래스)
    // /age/{birthYear} => 현재 나이를 계산해서 출력
    // /age/1990 => 36살 입니다
    @GetMapping("/age/{birthYear}")
    public String age(@PathVariable int birthYear, Model model){

         int year = Year.now().getValue();
        int age = year - birthYear;

        String message = age + "살 입니다.";
        model.addAttribute("message", message);
        return "age";
    }

    @GetMapping("/ping")
    public String ping(Model model){
        return "ping";
    }

    @GetMapping("/pong")
    public String pong(
            @RequestParam String title,
            @RequestParam String content,
            Model model){

        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "pong";
    }



}
