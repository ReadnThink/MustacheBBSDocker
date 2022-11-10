package com.prac221109.bbs221109.controller;

import com.prac221109.bbs221109.domain.dto.ArticleDto;
import com.prac221109.bbs221109.domain.entity.Article;
import com.prac221109.bbs221109.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public String list(Model model){
        List<Article> articles = articleRepository.findAll();
        //에러 확인
        for (Article article : articles) {
            System.out.println(
                    "id = " + article.getId() +
                            "     title = "+ article.getTitle() +
                            "     content = "+ article.getContent());
        }

        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/new")
    public String createNew(){
        return "articles/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()){
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message",String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model){
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(!optionalArticle.isEmpty()){
            System.out.println(optionalArticle.get());
            model.addAttribute("article", optionalArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model){
        log.info("title:{}  content:{}",articleDto.getTitle(),articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @PostMapping("/posts")
    public String add(ArticleDto articleDto){
        log.info("title = "+articleDto.getTitle() + "    content = "+articleDto.getContent());
        Article saveArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", saveArticle.getId());
    }

}
