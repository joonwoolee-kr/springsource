package com.example.board.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public PageResultDto<BoardDto, Object[]> getList(PageRequestDto requestDto) {
        Page<Object[]> result = boardRepository.list(requestDto.getType(), requestDto.getKeyword(),
                requestDto.getPageable(Sort.by("bno").descending()));

        Function<Object[], BoardDto> fn = (en -> entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public BoardDto read(Long bno) {
        Object[] object = boardRepository.getBoardByBno(bno);

        return entityToDto((Board) object[0], (Member) object[1], (Long) object[2]);
    }

    @Override
    public Long update(BoardDto dto) {
        // Board board = boardRepository.findById(dto.getBno()).get();
        // board.setTitle(dto.getTitle());
        // board.setContent(dto.getContent());
        // boardRepository.save(board);
        // return board.getBno();

        // 화면에서 writerEmail 넣을 경우 가능
        return boardRepository.save(dtoToEntity(dto)).getBno();
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }

}
