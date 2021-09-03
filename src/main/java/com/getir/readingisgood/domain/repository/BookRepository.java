package com.getir.readingisgood.domain.repository;

import com.getir.readingisgood.domain.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book>  findBookByIsbnNumber(String bookIsbnNumber);

}
