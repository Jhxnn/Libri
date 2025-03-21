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

import java.util.List;
import java.util.UUID;

@Service
public class FineService {

    @Autowired
    FineRepository fineRepository;

    @Autowired
    UserService userService;


    public List<Fine> findAll(){
        return fineRepository.findAll();
    }

    public Fine findById(UUID id){
        return fineRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }



    //FAZER LOGICA PARA CALCULAR A MULTA BASEADA NA DATA DE FIM DO LOAN E NA DATA ATUAL DA REQUISIÇÃO

    public Fine createFine(FineDto fineDto){
        var fine = new Fine();
        BeanUtils.copyProperties(fineDto,fine);
        var user = userService.findById(fineDto.userId());
        return fineRepository.save(fine);
    }

//    public Fine updateFine(){
//        fineRepos
//    }

    public void deleteFine(UUID id){
        var fine = findById(id);
        fineRepository.delete(fine);
    }
}
