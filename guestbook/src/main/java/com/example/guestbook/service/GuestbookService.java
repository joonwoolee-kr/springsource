package com.example.guestbook.service;

import com.example.guestbook.dto.GuestbookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.Guestbook;

public interface GuestbookService {

    // create
    Long register(GuestbookDto dto);

    // read
    GuestbookDto read(Long gno);

    PageResultDto<GuestbookDto, Guestbook> list(PageRequestDto requestDto);

    // update
    Long update(GuestbookDto dto);

    // delete
    void delete(Long gno);

    // dtoToEntity
    public default Guestbook dtoToEntity(GuestbookDto guestbookDto) {
        return Guestbook.builder()
                .gno(guestbookDto.getGno())
                .title(guestbookDto.getTitle())
                .content(guestbookDto.getContent())
                .writer(guestbookDto.getWriter())
                .build();
    }

    // entityToDto
    public default GuestbookDto entityToDto(Guestbook guestbook) {
        return GuestbookDto.builder()
                .gno(guestbook.getGno())
                .title(guestbook.getTitle())
                .content(guestbook.getContent())
                .writer(guestbook.getWriter())
                .regDate(guestbook.getRegDate())
                .updateDate(guestbook.getUpdateDate())
                .build();
    }
}
