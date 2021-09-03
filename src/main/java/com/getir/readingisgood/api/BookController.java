package com.getir.readingisgood.api;

import com.getir.readingisgood.api.command.request.AddBookCommand;
import com.getir.readingisgood.api.command.request.UpdateBookStockCommand;
import com.getir.readingisgood.api.command.response.BookDTO;
import com.getir.readingisgood.domain.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @NotNull}))
public class BookController {

    private final BookService bookService;

    @PostMapping(path = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> add(@RequestBody @Valid AddBookCommand command) {
        return ResponseEntity.ok(bookService.addBook(command));
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping(path = "/updatestock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> updateStockInfo(@RequestBody @Valid UpdateBookStockCommand command) {
        return ResponseEntity.ok(bookService.updateStockInfo(command));
    }
}
