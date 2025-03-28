package com.Libri.repositories;

import com.Libri.models.Book;
import com.Libri.models.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByLibrary(Library library);
}
