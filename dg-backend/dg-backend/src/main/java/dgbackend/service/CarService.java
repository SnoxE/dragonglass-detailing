package dgbackend.service;

import dgbackend.common.dto.ContentDto;
import dgbackend.common.exceptions.DgAuthException;
import dgbackend.database.car.CarDto;
import dgbackend.database.car.sql.CarSqlRow;
import dgbackend.database.car.sql.CarSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarService {

    @Autowired
    private final CarSqlService carSqlService;

    public CarService(CarSqlService carSqlService) {
        this.carSqlService = carSqlService;
    }

    public void addCar(
                   int userId,
                   String make,
                   String model,
                   String productionYear,
                   String size,
                   String colour) throws DgAuthException {

        carSqlService.createCar(userId, make, model, productionYear, size, colour);
    }

    public void deleteCar(int userId, int carId) {
        carSqlService.deleteCar(userId, carId);
    }

    public ContentDto<CarDto> getUserCars(String userId) {
        List<CarDto> carDtoList = carSqlService.getCars(userId).stream().map(CarService::carDtoMapper).toList();

        return new ContentDto<>(carDtoList);
    }

    private static CarDto carDtoMapper(CarSqlRow carSqlRow) {
    return new CarDto(
        String.valueOf(carSqlRow.id()),
        carSqlRow.make(),
        carSqlRow.model(),
        carSqlRow.productionYear(),
        carSqlRow.size(),
        carSqlRow.colour());
    }
}
