package com.Libri.services;

import com.Libri.dtos.LoanDto;
import com.Libri.models.Fine;
import com.Libri.models.Loan;
import com.Libri.models.enums.BookStatus;
import com.Libri.repositories.BookRepository;
import com.Libri.repositories.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    public List<Loan> findAll(){
        return loanRepository.findAll();
    }

    public Loan findById(UUID id){
        return loanRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public Loan createLoan(LoanDto loanDto){
        var loan = new Loan();
        BeanUtils.copyProperties(loanDto,loan);
        var book = bookService.findById(loanDto.bookId());
        book.setStatus(BookStatus.UNAVAILABLE);
        bookRepository.save(book);
        emailService.enviarEmailTexto(userService.findById(loanDto.userId()).email(),
                "Empréstimo de Livro",
                "Você retirou o Livro: " + bookService.findById(loanDto.bookId()).getTittle() + ". Data de devolução: " + loan.getEndDate());

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

    public Loan findByUser(UUID userId){
        var user = userService.findById1(userId);
        return loanRepository.findByUserId(user);
    }
}
