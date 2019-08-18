package app.controller;

import app.domain.Modification;
import app.domain.Renter;
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
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Rent> getRentsByRenterId(@PathVariable Long id) {
        return this.rentService.getRentsByRenterId(id);
    }

    @GetMapping(value="/{id}")
    public Rent getOne(@PathVariable Long id) {
        return this.rentService.getOne(id);
    }

    @GetMapping(value="/lastmodif")
    public Modification getLastModif() {
        return this.rentService.getLastModification();
    }

    @PostMapping(value="/save/{userId}")
    public Rent saveRent(@RequestBody Rent rent, @PathVariable Long userId) {
        return this.rentService.saveRent(rent, userId);
    }

    @DeleteMapping(value="/{rentId}/{userId}")
    public ResponseEntity deleteRent(@PathVariable Long rentId, @PathVariable Long userId) {
        this.rentService.deleteRent(rentId, userId);
        return ResponseEntity.noContent().build();
    }



}




