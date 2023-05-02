package com.fastcampus.projectboard.comment.repository;

import com.fastcampus.projectboard.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends
        JpaRepository<Comment, Long>
//        ,
//        QuerydslPredicateExecutor<Comment>,
//        QuerydslBinderCustomizer<QComment>
{


}
