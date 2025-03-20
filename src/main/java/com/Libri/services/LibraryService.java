package com.Libri.services;

import com.Libri.dtos.LibraryDto;
import com.Libri.models.Library;
import com.Libri.repositories.LibraryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LibraryService {

    @Autowired
    LibraryRepository libraryRepository;


    public List<Library> findAll(){
        return libraryRepository.findAll();
    }

    public Library findById(UUID id){
        return libraryRepository.findById(id).orElseThrow(()-> new RuntimeException("Cannot be found"));
    }

    public Library createLibrary(LibraryDto libraryDto){
        var library = new Library();
        BeanUtils.copyProperties(libraryDto,library);
        return libraryRepository.save(library);
    }

    public Library updateLibrary(LibraryDto libraryDto, UUID id){
        var library = findById(id);
        BeanUtils.copyProperties(libraryDto,library);
        return libraryRepository.save(library);
    }

    public void deleteLibrary(UUID id){
        var library = findById(id);
        libraryRepository.delete(library);
    }
}
