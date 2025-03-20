package com.Libri.controllers;

import com.Libri.dtos.BookDto;
import com.Libri.models.Book;
import com.Libri.services.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(name = "id")UUID id,
                                           @RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(bookDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable(name = "id")UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
