package com.example.memo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.memo.dto.MemoDto;
import com.example.memo.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@RequestMapping("/rest")
@Log4j2
@RestController // 화면단은 어떤 것이 들어와도 상관 없음, 데이터 전달
public class MemoRestController {

    private final MemoService memoService;

    @GetMapping("/list")
    public List<MemoDto> getList() {
        log.info("메모 전체 목록 요청");
        List<MemoDto> list = memoService.list();
        return list;
    }

    // /rest/1
    @GetMapping("/{mno}")
    public MemoDto getRead(@PathVariable Long mno) {
        log.info("메모 조회 {}", mno);

        MemoDto dto = memoService.read(mno);
        return dto;
    }

    // @RequestBody: json => 객체
    @PostMapping("/create")
    public ResponseEntity<String> postCreate(@RequestBody MemoDto dto) {
        log.info("메모 작성 {}", dto);

        Long mno = memoService.create(dto);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    // rest 추가되는 method: PUT(patch) / DELETE

    @PutMapping("/{mno}")
    public ResponseEntity<String> postMethodName(@PathVariable Long mno, @RequestBody MemoDto dto) {
        log.info("수정 요청 {}", dto);

        dto.setMno(mno);
        memoService.update(dto);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<String> getRemove(@PathVariable Long mno) {
        log.info("메모 삭제 요청 {}", mno);

        memoService.delete(mno);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
