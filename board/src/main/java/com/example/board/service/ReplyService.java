package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyService {
    Long register(ReplyDto replyDto);

    List<ReplyDto> list(Long bno);

    ReplyDto read(Long rno);

    Long modify(ReplyDto replyDto);

    void remove(Long rno);

    // entity => dto
    default ReplyDto entityToDto(Reply entity) {
        return ReplyDto.builder()
                .rno(entity.getRno())
                .replyer(entity.getReplyer())
                .text(entity.getText())
                .bno(entity.getBoard().getBno())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();

    }

    // dto => entity
    default Reply dtoToEntity(ReplyDto dto) {

        Board board = Board.builder().bno(dto.getBno()).build();

        return Reply.builder()
                .rno(dto.getRno())
                .replyer(dto.getReplyer())
                .text(dto.getText())
                .board(board)
                .build();
    }

}
