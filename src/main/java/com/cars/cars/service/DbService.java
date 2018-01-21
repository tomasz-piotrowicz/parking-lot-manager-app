package com.cars.cars.service;

import com.cars.cars.domain.Car;
import com.cars.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private CarRepository repository;

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public Optional<Car> getCar(final Long id){
        return repository.findById(id);
    }

    public Car saveCar(final Car car){
        return repository.save(car);
    }

    public void deleteCar(final Long id){
        repository.deleteById(id);
    }
}
