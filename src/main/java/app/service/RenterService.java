package app.service;

import app.dao.RenterDao;
import app.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class RenterService {

    @Autowired
    private RenterDao renterDao;


    public Renter getByName(String name) {
        return this.renterDao.findByName(name);
    }

}
