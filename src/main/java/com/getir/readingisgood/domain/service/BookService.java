package com.getir.readingisgood.domain.service;


import com.getir.readingisgood.api.command.request.AddBookCommand;
import com.getir.readingisgood.api.command.request.UpdateBookStockCommand;
import com.getir.readingisgood.api.command.response.BookDTO;
import com.getir.readingisgood.domain.model.exception.BookStockUpdateException;

import java.util.List;


public interface BookService {

    BookDTO addBook(AddBookCommand command);

    BookDTO getBook(String id);

    BookDTO updateStockInfo(UpdateBookStockCommand command) throws BookStockUpdateException;

    List<BookDTO> getAllBooks();
}
