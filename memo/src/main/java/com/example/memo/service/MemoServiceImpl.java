package com.example.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @Override
    public Long create(MemoDto dto) {
        // Memo memo = Memo.builder().memoText(dto.getMemoText()).build();
        Memo memo = dtoToEntity(dto);
        return memoRepository.save(memo).getMno();
    }

    @Override
    public MemoDto read(Long id) {
        Memo memo = memoRepository.findById(id).get();

        return entityToDto(memo);
    }

    @Override
    public List<MemoDto> list() {
        List<Memo> list = memoRepository.findAll();

        return list.stream().map(memo -> entityToDto(memo)).collect(Collectors.toList());
    }

    @Override
    public Long update(MemoDto dto) {
        // Memo memo = memoRepository.findById(27L).get();
        // memo.setMemoText("memo 수정");
        Memo memo = dtoToEntity(dto);
        return memoRepository.save(memo).getMno();
    }

    @Override
    public void delete(Long id) {
        memoRepository.deleteById(id);
    }

}
