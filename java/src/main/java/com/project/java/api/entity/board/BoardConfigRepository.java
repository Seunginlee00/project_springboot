package com.project.java.api.entity.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardConfigRepository extends JpaRepository<BoardConfig,Long>{
}