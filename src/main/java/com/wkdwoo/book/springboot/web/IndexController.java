package com.wkdwoo.book.springboot.web;

import com.wkdwoo.book.springboot.config.auth.LoginUser;
import com.wkdwoo.book.springboot.config.auth.dto.SessionUser;
import com.wkdwoo.book.springboot.domain.posts.PostsRepository;
import com.wkdwoo.book.springboot.service.posts.PostsService;
import com.wkdwoo.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // Model: 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        // 여기서는 postsService.findAllDesc()를 가져온 결과를 posts로 index.mustache에 전달

        model.addAttribute("posts", postsService.findAllDesc());
        
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
