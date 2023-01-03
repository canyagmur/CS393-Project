package com.example.canproject.service;


import com.example.canproject.entity.Services;
import com.example.canproject.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class ServicesService {
    @Autowired
    ServicesRepository servicesRepository;
    public Services save(Services service){
        try {
            Services result = servicesRepository.save(service);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Services findById(Long id){
        Optional<Services> result=servicesRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new EmptyResultDataAccessException(1);
        }
    }
    public List<Services> findAll(){
        List<Services> result= servicesRepository.findAll();
        if(result.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        else{
            return result;
        }
    }

}
