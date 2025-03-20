package com.Libri.services;

import com.Libri.dtos.BookDto;
import com.Libri.models.Book;
import com.Libri.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;


    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public  Book findById(UUID id){
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public Book createBook(BookDto bookDto){
        var book = new Book();
        BeanUtils.copyProperties(bookDto,book);
        return bookRepository.save(book);
    }

    public Book updateBook(BookDto bookDto, UUID id){
        var book = findById(id);
        BeanUtils.copyProperties(bookDto,book);
        return bookRepository.save(book);
    }

    public void deleteBook(UUID id){
        var book = findById(id);
        bookRepository.delete(book);
    }
}
