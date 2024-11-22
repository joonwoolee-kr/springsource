package com.example.board.service;

import java.util.List;

import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

public interface ReplyService {

    Long register(ReplyDto replyDto);

    List<ReplyDto> list(Long bno);

    ReplyDto read(Long rno);

    Long modify(ReplyDto replyDto);

    void remove(Long rno);

    // entity => dto
    default ReplyDto entityToDto(Reply entity) {
        ReplyDto dto = ReplyDto.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyerEmail(entity.getReplyer().getEmail())
                .replyerName(entity.getReplyer().getName())
                .bno(entity.getBoard().getBno())
                .regDate(entity.getRegDate())
                .updateDate(entity.getUpdateDate())
                .build();
        return dto;
    }

    // dto => entity
    default Reply dtoToEntity(ReplyDto dto) {

        Board board = Board.builder().bno(dto.getBno()).build();
        Member member = Member.builder().email(dto.getReplyerEmail()).build();

        Reply entity = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(member)
                .board(board)
                .build();
        return entity;
    }
}
