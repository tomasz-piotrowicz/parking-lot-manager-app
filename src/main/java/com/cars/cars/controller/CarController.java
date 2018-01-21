package com.cars.cars.controller;

import com.cars.cars.domain.Car;
import com.cars.cars.domain.CarDto;
import com.cars.cars.mapper.CarMapper;
import com.cars.cars.repository.CarRepository;
import com.cars.cars.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/car")
public class CarController {
    @Autowired
    private DbService service;
    @Autowired
    private CarMapper mapper;

    @Autowired
    private CarRepository carRepository;

    @RequestMapping(method = RequestMethod.GET, value = "getCars")
    public List<CarDto> getCars(){
        return mapper.mapToCarDtoList(service.getAllCars());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCar")
    public CarDto getCar(@RequestParam Long carId) throws CarNotFoundException {
        return mapper.mapToCarDto(service.getCar(carId).orElseThrow(CarNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCar")
    public void deleteCar(@RequestParam Long carId){
        service.deleteCar(carId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCar")
    public CarDto updateCar(@RequestBody CarDto carDto){
        return mapper.mapToCarDto(service.saveCar(mapper.mapToCar(carDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCar", consumes = APPLICATION_JSON_VALUE)
    public void createCar(@RequestBody CarDto carDto) throws NoPlaceForNewCarsException {
       if(carRepository.count() < 20){
            service.saveCar(mapper.mapToCar(carDto));
        } else {
            throw new NoPlaceForNewCarsException();
        }

    }
}
