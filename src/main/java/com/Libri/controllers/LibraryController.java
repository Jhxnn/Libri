package com.Libri.controllers;

import com.Libri.dtos.LibraryDto;
import com.Libri.models.Library;
import com.Libri.services.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(description = "Busca livraria por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Library> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findById(id));
    }

    @Operation(description = "Lista todas as livrarias")
    @GetMapping
    public ResponseEntity<List<Library>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.findAll());
    }


    @Operation(description = "Cria uma livraria")
    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody LibraryDto libraryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.createLibrary(libraryDto));
    }


    @Operation(description = "Atualiza uma livraria")
    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable(name = "id")UUID id,
                                                 @RequestBody LibraryDto libraryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.updateLibrary(libraryDto, id));
    }


    @Operation(description = "Deleta uma livraria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable(name = "id")UUID id){
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}
