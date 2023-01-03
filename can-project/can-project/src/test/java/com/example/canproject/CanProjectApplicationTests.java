package com.example.canproject;

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
		car.setBrand("Ferrari");
		car.setModel("488 Pista");
		car.setBarcode(3L);

		CarService carService = new CarService();
		CarCreaterDTO carResult = carService.findByBarcode(3L);

		assertNotNull(carResult);
		assertEquals(car.getBarcode(), String.valueOf(carResult.getBarcode()));
	}

}
