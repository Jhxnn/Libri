package com.Libri.services;

import com.Libri.dtos.FineDto;
import com.Libri.models.Fine;
import com.Libri.models.User;
import com.Libri.repositories.FineRepository;
import jakarta.validation.constraints.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class FineService {

    @Autowired
    FineRepository fineRepository;

    @Autowired
    UserService userService;

    @Autowired
    LoanService loanService;

    @Autowired
    EmailService emailService;


    public List<Fine> findAll(){
        return fineRepository.findAll();
    }

    public Fine findById(UUID id){
        return fineRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }




    public Fine createFine(FineDto fineDto){

        var fine = new Fine();
        var user = userService.findById1(fineDto.userId());
        var loan = loanService.findByUser(user.getUserid());
        long dias = ChronoUnit.DAYS.between(loan.getEndDate(), LocalDate.now());

        if(dias > 0) {
            if(dias > 30){
                double valorMulta = dias * 2.5 + 30;
                fine.setValue(valorMulta);
                emailService.enviarEmailTexto(user.getEmail(),
                        "Atraso na devolução: multa",
                        "A devolução está atrasada em " + dias + " dias. A cada dia de atraso é cobrado R$2,50. \nA multa atual está em R$" + valorMulta + ", pelo atraso de mais de 30 dias o valor da multa é somado em R$30,00");
            }
            else{
                double valorMulta = dias * 2.5;
                fine.setValue(valorMulta);
                emailService.enviarEmailTexto(user.getEmail(),
                        "Atraso na devolução: multa",
                        "A devolução está atrasada em " + dias + " dias. A cada dia de atraso é cobrado R$2,50. \nA multa atual está em R$" + valorMulta + ".\nPague o quanto antes para podermos dar seguimento no processo de liberação.");

            }
            fine.setTime(LocalDate.now());
            fine.setUser(user);
            return fineRepository.save(fine);



        }

        fine.setTime(LocalDate.now());
        fine.setUser(user);
        return fineRepository.save(fine);


    }

    public void deleteFine(UUID id){
        var fine = findById(id);
        fineRepository.delete(fine);
    }
}
