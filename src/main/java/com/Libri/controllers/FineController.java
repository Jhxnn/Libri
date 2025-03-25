package com.Libri.controllers;

import com.Libri.dtos.FineDto;
import com.Libri.models.Fine;
import com.Libri.services.FineService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fine")
public class FineController {

    @Autowired
    FineService fineService;

    @Operation(description = "Busca multa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Fine> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(fineService.findById(id));
    }

    @Operation(description = "Lista todas as multas")
    @GetMapping
    public ResponseEntity<List<Fine>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(fineService.findAll());
    }


    @Operation(description = "Cria uma multa")
    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody FineDto fineDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(fineService.createFine(fineDto));
    }



    @Operation(description = "Deleta uma multa")
    @DeleteMapping("/{id}")
    public ResponseEntity<Fine> deleteFine(@PathVariable(name = "id")UUID id){
        fineService.deleteFine(id);
        return ResponseEntity.noContent().build();
    }
}
