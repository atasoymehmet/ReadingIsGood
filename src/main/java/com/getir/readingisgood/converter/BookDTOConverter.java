package com.getir.readingisgood.converter;

import com.getir.readingisgood.api.command.response.BookDTO;
import com.getir.readingisgood.domain.model.Book;
import lombok.NonNull;
import org.springframework.stereotype.Component;


@Component
public class BookDTOConverter extends BaseConverter<Book, BookDTO> {
    @Override
    public BookDTO convert(@NonNull Book source) {
        return BookDTO.builder()
                .id(source.getId())
                .isbnNumber(source.getIsbnNumber())
                .bookName(source.getBookName())
                .stockCount(source.getStockCount())
                .price(source.getPrice())
                .build();
    }
}
