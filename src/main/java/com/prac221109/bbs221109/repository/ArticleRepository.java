package com.prac221109.bbs221109.repository;

import com.prac221109.bbs221109.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
