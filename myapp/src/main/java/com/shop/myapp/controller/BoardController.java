package com.shop.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.myapp.dto.BoardRequestDTO;
import com.shop.myapp.dto.BoardResponseDTO;
import com.shop.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	//게시판 목록 페이지
    @GetMapping("/list")
    public String openBoardList(Model model) {
    	List<BoardResponseDTO> boardList = boardService.findAll();
    	model.addAttribute("boardList", boardList);
        return "board/list";
    }

    //게시판 글 상세보기
    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam("id") long id, Model model) {
    	BoardResponseDTO board = boardService.findById(id);
    	model.addAttribute("board", board);
        return "board/detail";
    }
    
    //게시판 글쓰기 페이지 로딩
    @GetMapping("/write")
    public String openBoardWrite() {
        return "board/write";
    }

    //게시판 글쓰기
    @PostMapping("/boards")
    public Long save(@RequestBody final BoardRequestDTO params) {
    	return boardService.save(params);
    	//return "board/list";
    }
    
    //게시판 글 수정 폼 업로드
    @GetMapping("/update")
    public String updateBoard(@RequestParam("id") long id, Model model) {
    	BoardResponseDTO board = boardService.findById(id);
    	model.addAttribute("board", board);
        return "board/update";
    }
    
    //게시판 글 수정 처리하기
    @PostMapping("/update")
    public Long updateBoardPro(@RequestParam("id") long id, @RequestBody final BoardRequestDTO params) {
    	return boardService.update(id, params);
    }
}