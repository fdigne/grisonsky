package app.service;

import app.dao.RenterDao;
import app.domain.Login;
import app.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RenterService {

    @Autowired
    private RenterDao renterDao;


    public Renter getByName(String name) {
        Renter renter = this.renterDao.findByName(name);
        return renter;
    }

    public List<Renter> getRenters(Long id) {
        return this.renterDao.getRenters(id);
    }

    public Renter login(Login login) {
        Renter renterToCheck = this.renterDao.findByName(login.getLogin());
        if (renterToCheck != null && renterToCheck.getPassword().equals(login.getPassword())) {
            return renterToCheck;
        }
        else {
            return null;
        }
    }

}
