package com.example.guestbook.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestbookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDto dto) {
        return null;
    }

    @Override
    public GuestbookDto read(Long gno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'read'");
    }

    @Override
    public PageResultDto<GuestbookDto, Guestbook> list(PageRequestDto requestDto) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        Page<Guestbook> result = guestbookRepository.findAll(
                guestbookRepository.makePredicate(requestDto.getType(), requestDto.getKeyword()),
                pageable);

        Function<Guestbook, GuestbookDto> fn = (entity -> entityToDto(entity));

        return new PageResultDto<>(result, fn);
    }

    @Override
    public Long update(GuestbookDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long gno) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
