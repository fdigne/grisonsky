package app.controller;

import app.domain.Renter;
import app.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renter")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class RenterController {


    @Autowired
    private RenterService renterService;

    @GetMapping(value="/{name}")
    public Renter getByName(@PathVariable String name) {
        return this.renterService.getByName(name);
    }
}




