package com.example.memo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.memo.dto.MemoDto;
import com.example.memo.service.MemoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/memo")
@Controller
public class MemoController {

    private final MemoService memoService;

    // 메모작성 : /memo/create : get, post
    @GetMapping("/create")
    public void getCreateForm(MemoDto dto) {
        log.info("메모 작성 폼 요청");
    }

    @PostMapping("/create")
    public String postCreate(@Valid MemoDto dto, BindingResult result, RedirectAttributes rttr) {
        log.info("메모 작성 {}", dto);

        // 유효성 검증
        if (result.hasErrors()) {
            return "/memo/create";
        }

        Long mno = memoService.create(dto);

        rttr.addFlashAttribute("msg", mno + "번 메모가 생성되었습니다.");
        return "redirect:list";
    }

    // 전체메모 : /memo/list : get
    @GetMapping("/list")
    public void getList(Model model) {
        log.info("메모 전체 목록 요청");
        List<MemoDto> list = memoService.list();
        model.addAttribute("list", list);
    }

    // 메모수정(메모조회 + 수정) : /memo/read?mno=1 /memo/update?mno=1 : get, post
    @GetMapping(path = { "/read", "/update" })
    public void getRead(@RequestParam Long mno, Model model) {
        log.info("메모 조회 {}", mno);

        MemoDto dto = memoService.read(mno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/update")
    public String postMethodName(MemoDto dto, RedirectAttributes rttr) {
        log.info("수정 요청 {}", dto);

        Long mno = memoService.update(dto);

        rttr.addFlashAttribute("msg", mno + "번 메모가 수정되었습니다.");
        return "redirect:list";
    }

    // 메모삭제 : /memo/remove?mno=1 : get
    @GetMapping("/remove")
    public String getRemove(@RequestParam Long mno, RedirectAttributes rttr) {
        log.info("메모 삭제 요청 {}", mno);

        memoService.delete(mno);

        rttr.addFlashAttribute("msg", mno + "번 메모가 삭제되었습니다.");
        return "redirect:list";
    }

}
