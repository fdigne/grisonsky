package app.controller;

import app.service.RentService;
import app.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {


	@Autowired
	private RentService rentService;

	@GetMapping(value="/all")
	public List<Rent> all() {
		return this.rentService.getAll();
	}

	@GetMapping(value="/{id}")
	public Rent getOne(@PathVariable Long id) {
		return this.rentService.getOne(id);
	}

	@PostMapping(value="/save")
	public Rent saveRent(@RequestBody Rent rent) {
		return this.rentService.saveRent(rent);
	}

	@DeleteMapping(value="/{id}")
	public String deleteRent(@PathVariable Long id) {
		return this.rentService.deleteRent(id);
	}



}




