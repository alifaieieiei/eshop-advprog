package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    // DIP

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        // DIP
    }

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
        // SRP
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> cars = new ArrayList<>();
        carIterator.forEachRemaining(cars::add);
        return cars;
        // SRP
    }

    @Override
    public Car findById(String carId) {
        return carRepository.findById(carId);
        // SRP
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
        // SRP
    }

    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
        // SRP
    }
}