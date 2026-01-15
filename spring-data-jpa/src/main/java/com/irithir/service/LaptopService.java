package com.irithir.service;

import com.irithir.model.Laptop;
import com.irithir.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaptopService {
    private LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public void saveLaptop(Laptop laptop){
        laptopRepository.save(laptop);
    }

    public Laptop getLaptopById(Long id){
        Optional<Laptop> optionalLaptop = laptopRepository.findById(id);
        return optionalLaptop.orElse(null);
    }
}
