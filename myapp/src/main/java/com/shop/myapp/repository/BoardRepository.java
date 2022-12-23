package com.shop.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.myapp.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}