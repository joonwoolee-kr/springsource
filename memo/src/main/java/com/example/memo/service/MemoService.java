package com.example.memo.service;

import java.util.List;

import com.example.memo.dto.MemoDto;
import com.example.memo.entity.Memo;

public interface MemoService {
    // crud 메소드
    Long create(MemoDto dto);

    MemoDto read(Long id);

    List<MemoDto> list();

    Long update(MemoDto dto);

    void delete(Long id);

    // dto ==> entity
    public default Memo dtoToEntity(MemoDto dto) {
        return Memo.builder()
                .mno(dto.getMno())
                .memoText(dto.getMemoText())
                .build();
    }

    // entity ==> dto
    public default MemoDto entityToDto(Memo memo) {
        return MemoDto.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .createdDateTime(memo.getCreatedDateTime())
                .lastModifiedDateTime(memo.getLastModifiedDateTime())
                .build();
    }
}
