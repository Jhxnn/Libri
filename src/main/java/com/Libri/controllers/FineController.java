package com.Libri.controllers;

import com.Libri.dtos.FineDto;
import com.Libri.models.Fine;
import com.Libri.services.FineService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Fine> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(fineService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Fine>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(fineService.findAll());
    }

    @PostMapping
    public ResponseEntity<Fine> createFine(@RequestBody FineDto fineDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(fineService.createFine(fineDto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Fine> deleteFine(@PathVariable(name = "id")UUID id){
        fineService.deleteFine(id);
        return ResponseEntity.noContent().build();
    }
}
