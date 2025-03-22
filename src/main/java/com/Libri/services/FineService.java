package com.Libri.services;

import com.Libri.dtos.FineDto;
import com.Libri.models.Fine;
import com.Libri.models.User;
import com.Libri.repositories.FineRepository;
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
            fine.setValue(dias * 2.5);
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
