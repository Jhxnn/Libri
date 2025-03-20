package com.Libri.controllers;

import com.Libri.dtos.LibraryDto;
import com.Libri.models.Library;
import com.Libri.services.LibraryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("/{id}")
    public ResponseEntity<Library> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Library>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody LibraryDto libraryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.createLibrary(libraryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable(name = "id")UUID id,
                                                 @RequestBody LibraryDto libraryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.updateLibrary(libraryDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable(name = "id")UUID id){
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}
