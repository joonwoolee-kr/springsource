package com.example.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;
import com.example.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDto replyDto) {
        return replyRepository.save(dtoToEntity(replyDto)).getRno();
    }

    @Override
    public List<ReplyDto> list(Long bno) {
        Board board = Board.builder().bno(bno).build();
        List<Reply> list = replyRepository.findByBoardOrderByRno(board);

        return list.stream().map(reply -> entityToDto(reply)).collect(Collectors.toList());
    }

    @Override
    public ReplyDto read(Long rno) {
        return entityToDto(replyRepository.findById(rno).get());
    }

    @Override
    public Long modify(ReplyDto replyDto) {

        Reply reply = replyRepository.findById(replyDto.getRno()).get();
        // 내용 수정
        reply.setText(replyDto.getText());
        return replyRepository.save(reply).getRno();
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

}
