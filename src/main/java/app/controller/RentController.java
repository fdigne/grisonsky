package app.controller;

import app.service.RentService;
import app.domain.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RentController {


    @Autowired
    private RentService rentService;

    @GetMapping(value="/all/{id}")
    public List<Rent> getRentsByRenterId(@PathVariable Long id) {
        return this.rentService.getRentsByRenterId(id);
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
    public ResponseEntity deleteRent(@PathVariable Long id) {
        this.rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }



}




