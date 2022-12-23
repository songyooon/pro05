package com.shop.myapp.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.shop.myapp.dto.BoardRequestDto;
import com.shop.myapp.dto.BoardResponseDto;
import com.shop.myapp.entity.Board;
import com.shop.myapp.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	
	//글 등록
	@Transactional
	public Long save(final BoardRequestDto params) {
		Board entity = boardRepository.save(params.toEntity());
		return entity.getId();
	}
	
	//글 목록 
	public List<BoardResponseDto> findAll() {
			Sort sort = Sort.by(Direction.DESC, "id", "createdDate");
			List<Board> list = boardRepository.findAll();
			return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());
	}
	
	//글 수정
	public Long update(final Long id, BoardRequestDto params) {
		Board entity = boardRepository.findById(id).orElseThrow();
		entity.update(params.getTitle(), params.getContent(), params.getWriter());
		return id;
	}
	
	//글 삭제
	@Transactional
    public Long delete(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow();
        entity.delete();
        return id;
    }
	
	//게시글 상세보기
	@Transactional
    public BoardResponseDto findById(final Long id) {
        Board entity = boardRepository.findById(id).orElseThrow();
        entity.increaseHits();
        return new BoardResponseDto(entity);
    }
	
	//type을 파라미터로 받아 선택처리하는 경우
	public boolean executeQueryByType(final String type, final Long id, final BoardRequestDto params) {
	    // INSERT
	    if (StringUtils.equals(type, "INSERT")) {
	        boardRepository.save(params.toEntity());
	        return true;
	    }
	    Board entity = boardRepository.findById(id).orElseThrow();
	    switch (type) {
	    case "UPDATE": // UPDATE
	        entity.update(params.getTitle(), params.getContent(), params.getWriter());
	        break;

	    case "DELETE": // DELETE
	        entity.delete();
	        break;
	    }
	    return true;
	}
}