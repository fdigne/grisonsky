package app.controller;

import app.service.RentService;
import app.domain.Rent;
import app.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {


	@Autowired
	private RentService rentService;

	@GetMapping(value="/all")
	List<Rent> all() {
		return this.rentService.getAll();
	}


}




