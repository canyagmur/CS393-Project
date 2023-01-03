package com.example.canproject;

import com.example.canproject.DTO.AvailableCarByTypeDTO;
import com.example.canproject.DTO.CarCreaterDTO;
import com.example.canproject.entity.Car;
import com.example.canproject.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CanProjectApplicationTests {



	@Test
	void testFindByCarBarcode(){
		Car car;
		car = new Car();
		car.setBarcode(15L);
		car.setBrand("Ferrari");
		car.setModel("488 Pista");

		CarService carService = new CarService();
		CarCreaterDTO carResult = carService.findByBarcode(15L);

		assertNotNull(carResult);
		assertEquals(car.getBarcode(), String.valueOf(carResult.getBarcode()));
	}

	/*
        public CarCreaterDTO(){

    }
     */
	@Test
	void saveCar(){
		Car car = new Car();
		car.setBarcode(13L);
		car.setBrand("Ferrari");
		car.setModel("F40");


		CarService carService = new CarService();
		carService.save(new CarCreaterDTO());


	}

	@Test
	void deleteCars(){
		Car car = new Car();
		car.setBarcode(12L);
		car.setBrand("Ferrari");
		car.setModel("F12");

		CarService carService =  new CarService();
		carService.deleteById(car.getBarcode());
	}

	@Test
	void getAllAvailableCars(){
		Car car = new Car();
		car.setBarcode(13L);
		car.setBrand("Ferrari");
		car.setModel("812 Compitozone");

		CarService carService =  new CarService();
		carService.getAvailableCars(new AvailableCarByTypeDTO(car.getTransmissionType(), car.getType().toString()));

	}
	@Test
	void getRentedCars(){
		Car car = new Car();
		car.setBarcode(13L);
		car.setBrand("Porsche");
		car.setModel("911 GT3");


		CarService carService =  new CarService();
		CarCreaterDTO carResult = (CarCreaterDTO) carService.getAllRentedCars();

		assertNotNull(carResult);


	}

}
