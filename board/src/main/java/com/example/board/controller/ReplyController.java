package com.example.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDto;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RequestMapping("/replies")
@Log4j2
@RestController
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/board/{bno}")
    public ResponseEntity<List<ReplyDto>> list(@PathVariable Long bno) {
        log.info("{} 댓글 요청", bno);
        List<ReplyDto> replies = replyService.list(bno);
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

}
