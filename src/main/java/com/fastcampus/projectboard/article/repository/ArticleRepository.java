package com.fastcampus.projectboard.article.repository;

import com.fastcampus.projectboard.article.entity.Article;
import com.fastcampus.projectboard.article.entity.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//프로젝트의 repository를 rest API로 노출을 시킬것인지의 전략
@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article,Long>,
        //Article 안에 있는 모든 필드에 있는 기본 검색 기능 추가
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        //현재 Article에 있는 모든 필드에 대한 검색을 허용하지만 true를 통해 선택적인 것만 검색을 할 수 있게 한다.
        bindings.excludeUnlistedProperties(true);

        //검색 가능한 필드 추가
        bindings.including(root.title, root.content,root.hashtag,root.createdBy,root.createdAt);

        //bind() : 필터링 할 컬럼을 매개값으로 넘겨줌
        //exact 매치 룰을 바꾸기 위한 bind 메서드
        //first : 검색 파라미터 한개만 받음
        //likeIgnoreCase : 쿼리문이 like ''
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);
        //containsIgnorCase : 문자열로 필터를 했을 때 대소문자를 구별하지 않고 찾음
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
