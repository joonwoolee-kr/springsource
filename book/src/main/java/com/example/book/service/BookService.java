package com.example.book.service;

import java.util.List;

import com.example.book.dto.BookDto;
import com.example.book.dto.CategoryDto;
import com.example.book.dto.PublisherDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

public interface BookService {

    // C
    Long create(BookDto dto);

    // R
    BookDto getRow(Long id);

    List<BookDto> getList();

    // U
    Long update(BookDto dto);

    // D
    void delete(Long id);

    List<CategoryDto> getCateList();

    List<PublisherDto> getPubList();

    public default CategoryDto entityToDto(Category entity) {
        return CategoryDto.builder()
                .id(entity.getId())
                .categoryName(entity.getName())
                .build();
    }

    public default Category dtoToEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getCategoryName())
                .build();
    }

    public default PublisherDto entityToDto(Publisher entity) {
        return PublisherDto.builder()
                .id(entity.getId())
                .publisherName(entity.getName())
                .build();
    }

    public default Publisher dtoToEntity(PublisherDto dto) {
        return Publisher.builder()
                .id(dto.getId())
                .name(dto.getPublisherName())
                .build();
    }

    public default BookDto entityToDto(Book entity) {
        return BookDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .writer(entity.getWriter())
                .price(entity.getPrice())
                .salePrice(entity.getSale_price())
                .categoryName(entity.getCategory().getName())
                .publisherName(entity.getPublisher().getName())
                .createdDateTime(entity.getCreatedDateTime())
                .lastModifiedDateTime(entity.getLastModifiedDateTime())
                .build();
    }

    public default Book dtoToEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .writer(dto.getWriter())
                .price(dto.getPrice())
                .sale_price(dto.getSalePrice())
                .category(Category.builder().id(Long.parseLong(dto.getCategoryName())).build())
                .publisher(Publisher.builder().id(Long.parseLong(dto.getPublisherName())).build())
                .build();
    }
}
