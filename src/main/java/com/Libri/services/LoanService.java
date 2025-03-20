package com.Libri.services;

import com.Libri.dtos.LoanDto;
import com.Libri.models.Loan;
import com.Libri.repositories.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;


    public List<Loan> findAll(){
        return loanRepository.findAll();
    }

    public Loan findById(UUID id){
        return loanRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public Loan createLoan(LoanDto loanDto){
        var loan = new Loan();
        BeanUtils.copyProperties(loanDto,loan);
        return loanRepository.save(loan);
    }

    public Loan updateLoan(LoanDto loanDto, UUID id){
        var loan = findById(id);
        BeanUtils.copyProperties(loanDto,loan);
        return loanRepository.save(loan);
    }

    public void deleteLoan(UUID id){
        var loan = findById(id);
        loanRepository.delete(loan);
    }
}
