package com.fastcampus.projectboard.comment.repository;

import com.fastcampus.projectboard.article.entity.QArticle;
import com.fastcampus.projectboard.comment.entity.Comment;
import com.fastcampus.projectboard.comment.entity.QComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends
        JpaRepository<Comment, Long>,
        QuerydslPredicateExecutor<Comment>,
        QuerydslBinderCustomizer<QComment> {

    @Override
    default void customize(QuerydslBindings bindings, QComment root) {
        //현재 Article에 있는 모든 필드에 대한 검색을 허용하지만 true를 통해 선택적인 것만 검색을 할 수 있게 한다.
        bindings.excludeUnlistedProperties(true);

        //검색 가능한 필드 추가
        bindings.including(root.content, root.createdBy, root.createdAt);

        //exact 매치 룰을 바꾸기 위한 bind 메서드
        //first : 검색 파라미터 한개만 받음
        //likeIgnoreCase : 쿼리문이 like ''
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);
        //containsIgnorCase : like '%s{v}%'

        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

    }
}