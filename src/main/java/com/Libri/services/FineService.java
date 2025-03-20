package com.Libri.services;

import com.Libri.dtos.FineDto;
import com.Libri.models.Fine;
import com.Libri.repositories.FineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FineService {

    @Autowired
    FineRepository fineRepository;


    public List<Fine> findAll(){
        return fineRepository.findAll();
    }

    public Fine findById(UUID id){
        return fineRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public Fine createFine(FineDto fineDto){
        var fine = new Fine();
        BeanUtils.copyProperties(fineDto,fine);
        return fineRepository.save(fine);
    }

    public Fine updateFine(FineDto fineDto, UUID id){
        var fine = findById(id);
        BeanUtils.copyProperties(fineDto,fine);
        return fineRepository.save(fine);
    }

    public void deleteFine(UUID id){
        var fine = findById(id);
        fineRepository.delete(fine);
    }
}
