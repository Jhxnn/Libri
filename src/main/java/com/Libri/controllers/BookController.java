package com.Libri.controllers;

import com.Libri.dtos.BookDto;
import com.Libri.dtos.GoogleBookDto;
import com.Libri.models.Book;
import com.Libri.services.BookService;
import com.Libri.services.GoogleBooksService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    GoogleBooksService googleBooksService;


    @Operation(description = "Lista livro pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @Operation(description = "Busca livro por meio da API Google Books")
    @GetMapping("/search")
    public List<GoogleBookDto> searchBooks(@RequestParam String query) {
        return googleBooksService.searchBooks(query);
    }


    @Operation(description = "Lista todos os livros")
    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @Operation(description = "Lista todos os livros de uma biblioteca")
    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<Book>> findByLibrary(@PathVariable(name = "libraryId")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findByLibrary(id));
    }


    @Operation(description = "Registra livro")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDto));
    }


    @Operation(description = "Atualiza um livro")
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(name = "id")UUID id,
                                           @RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(bookDto, id));
    }



    @Operation(description = "Deleta um livro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable(name = "id")UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
