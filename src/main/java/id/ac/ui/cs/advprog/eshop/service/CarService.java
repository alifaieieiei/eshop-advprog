package id.ac.ui.cs.advprog.eshop.service;

// Interface Segregation Principle
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

interface CarCreateService {
    public Car create(Car car);
}

interface CarReadService {
    public List<Car> findAll();
    Car findById(String carId);
}

interface CarUpdateService {
    public void update(String carId, Car car);
}

interface CarDeleteService {
    public void deleteCarById(String carId);
}

// Then CarService could extend all of them if needed
public interface CarService extends CarCreateService, CarReadService, CarUpdateService, CarDeleteService {
}