package com.cars.cars.mapper;

import com.cars.cars.domain.Car;
import com.cars.cars.domain.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CarMapper {
    public Car mapToCar(final CarDto carDto){
        return new Car(
                carDto.getId(),
                carDto.getModel(),
                carDto.getYear(),
                carDto.getRegNumber());
    }

    public CarDto mapToCarDto(final Car car){
        return new CarDto(
                car.getId(),
                car.getModel(),
                car.getYear(),
                car.getRegNumber());
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList){
        return carList.stream()
                .map(t -> new CarDto(t.getId(),t.getModel(),t.getYear(), t.getRegNumber()))
                .collect(Collectors.toList());
    }
}
