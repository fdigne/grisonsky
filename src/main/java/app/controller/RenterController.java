package app.controller;

import app.domain.Login;
import app.domain.Renter;
import app.service.RenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renter")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RenterController {


    @Autowired
    private RenterService renterService;

    @GetMapping(value="/{name}")
    public Renter getByName(@PathVariable String name) {
        return this.renterService.getByName(name);
    }

    @GetMapping(value="/all/{id}")
    public List<Renter> getRenters(@PathVariable Long id) {
        return this.renterService.getRenters(id);
    }

    @PostMapping(value="/login")
    public Renter saveRent(@RequestBody Login login) {
        return this.renterService.login(login);
    }
}