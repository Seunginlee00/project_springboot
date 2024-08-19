package com.java.project.api.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findByBoard(Board board);

}
