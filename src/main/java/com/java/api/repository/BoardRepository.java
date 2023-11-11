package com.java.api.repository;

import com.java.api.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

//@Repository
@NoRepositoryBean
public interface BoardRepository extends JpaRepository<Board, Long> {
}
