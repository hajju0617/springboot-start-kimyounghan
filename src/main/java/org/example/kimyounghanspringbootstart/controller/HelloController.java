package org.example.kimyounghanspringbootstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // 웹 어플리케이션에서 /hello 로 들어오면 이 메소드를 호출함.
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        // ctrl + p 하면 parameter info 를 볼 수 있음
        // -> @RequestParam(value = "name", required = true) 여기서 required 는 true가 default라 값을 무조건 넣어줘야 함
        // 따라서 localhost:8080/hello-mvc 하면 bad request 400 에러가 뜨고 localhost:8080/hello-mvc?name= 이렇게 적어주면 됨
        // ?name = -> http get 방식에서 parameter 를 넘겨줄 수 있음
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // html에 나오는 body 태그를 얘기하는 게 아니라 http에서 헤더부, 바디부가 있는 데
                    // 그 바디부에 이 데이터를 내가 직접 넣어 주겠다는 뜻 (응답 바디부에 이 내용을 직접 넣어 주겠다)
                    // 템블릿 없이 데이터를 그대로 보냄.
                    // @ResponseBody 가 있으면 viewResolver 대신 HttpMessageConverter 가 동작
                    // 단순 문자일 경우 -> StringConverter, 객체일 경우 -> JsonConverter 가 동작
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")    // 일반적으로 Spring에서 말하는 api 방식은 객체를 반환하는 것.
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 객체를 return
                        // 객체가 반환되면 기본 디폴트 값으로 JSON 방식으로 데이터를 만들어서 HTTP 응답에 반환
                        // 객체를 JSON으로 바꿔주는 라이브러리 2가지 -> Jackson(Spring 디폴트), Gson(구글에서 만듦)
        // localhost:8080/hello-api?name=spring!!! 으로 url을 적으면
        // 화면에 JSON 방식으로 뜸
        // {"name":"spring!!!"}
    }

    static class Hello {    // static class로 만들면 클래스 안에 클래스를 또 쓸 수 있음.
        private String name;    // private 이니까 외부에서 값을 못 꺼냄.
                                // 메서드를 통해서 접근.
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        // 만들어진 Getter/Setter : 이것을 JavaBean 규약이라고 함.
    }
}
