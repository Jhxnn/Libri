package com.Libri.controllers;

import com.Libri.dtos.LibraryDto;
import com.Libri.dtos.LoanDto;
import com.Libri.models.Library;
import com.Libri.models.Loan;
import com.Libri.services.LibraryService;
import com.Libri.services.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;


    @Operation(description = "Lista emprestimo pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Loan> findById(@PathVariable(name = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findById(id));
    }


    @Operation(description = "Lista todos os emprestimos")
    @GetMapping
    public ResponseEntity<List<Loan>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(loanService.findAll());
    }


    @Operation(description = "Cria um emprestimo")
    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDto loanDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoan(loanDto));
    }


    @Operation(description = "Atualiza um emprestimo")
    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable(name = "id")UUID id,
                                                 @RequestBody LoanDto loanDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.updateLoan(loanDto, id));
    }


    @Operation(description = "Deleta um emprestimo")
    @DeleteMapping("/{id}")
    public ResponseEntity<Loan> deleteLoan(@PathVariable(name = "id")UUID id){
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}
