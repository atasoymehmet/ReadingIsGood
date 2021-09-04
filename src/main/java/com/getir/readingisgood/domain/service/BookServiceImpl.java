package com.getir.readingisgood.domain.service;

import com.getir.readingisgood.api.command.request.AddBookCommand;
import com.getir.readingisgood.api.command.request.UpdateBookStockCommand;
import com.getir.readingisgood.api.command.response.BookDTO;
import com.getir.readingisgood.converter.BookDTOConverter;
import com.getir.readingisgood.domain.model.Book;
import com.getir.readingisgood.domain.model.exception.BookAlreadyExistException;
import com.getir.readingisgood.domain.model.exception.BookStockUpdateException;
import com.getir.readingisgood.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookDTOConverter bookDTOConverter;

    @Override
    public BookDTO addBook(AddBookCommand command) {
        log.debug("Book: Add Book started: {} ", command);
        bookRepository.findBookByIsbnNumber(command.getIsbnNumber())
                .ifPresent(x -> {
                    throw new BookAlreadyExistException("This book is already exist.");
                });
        BookDTO bookDTO = bookDTOConverter.convert(bookRepository.save(Book.builder()
                .bookName(command.getBookName())
                .isbnNumber(command.getIsbnNumber())
                .stockCount(command.getStockCount())
                .price(command.getPrice())
                .build()));

        log.debug("Book: Add Book completed: {} ", bookDTO);
        return bookDTO;
    }

    @Override
    public BookDTO getBook(String bookIsbnNumber) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbnNumber(bookIsbnNumber);
        BookDTO bookDTO = bookOptional.isPresent() ? bookDTOConverter.convert(bookOptional.get()) : BookDTO.builder().build();
        log.debug("Book: getBook completed: bookIsbnNumber: {} bookDTO: {}", bookIsbnNumber, bookDTO);
        return bookDTO;
    }

    @Override
    public BookDTO updateStockInfo(UpdateBookStockCommand command) throws BookStockUpdateException {
        log.debug("Book: updateStockInfo started: {} ", command);

        try {
            bookRepository.findById(command.getId())
                    .ifPresent(x -> {
                        x.setStockCount(command.getStock());
                        bookRepository.save(x);
                        log.debug("Book: updateStockInfo - stock info updated: {} ", x);
                    });

            Optional<Book> bookOptional = bookRepository.findById(command.getId());
            return bookOptional.isPresent() ? bookDTOConverter.convert(bookOptional.get()) : BookDTO.builder().build();

        } catch (Exception e) {
            log.error("Book: The stock info could not be updated. BookId : {}", command.getId());
            throw new BookStockUpdateException("Stock update exception");
        }
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOList = bookRepository.findAll()
                .stream()
                .map(bookDTOConverter::convert)
                .collect(Collectors.toList());
        log.debug("Book: getAllBooks completed: {} ", bookDTOList);
        return bookDTOList;
    }
}
