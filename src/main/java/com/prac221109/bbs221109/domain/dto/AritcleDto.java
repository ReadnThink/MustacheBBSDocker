package com.prac221109.bbs221109.domain.dto;

import com.prac221109.bbs221109.domain.entity.Article;
import lombok.Getter;

@Getter
public class AritcleDto {
    private String id;
    private String title;
    private String content;

    public AritcleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity(){
        return new Article(this.title, this.content);
    }
}
