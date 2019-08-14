package app.service;

import app.dao.ClientDao;
import app.dao.PeriodDao;
import app.dao.RenterDao;
import app.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Rent;

import app.dao.RentDao;

import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class RentService {

    @Autowired
    private RentDao rentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RenterDao renterDao;

    @Autowired
    private PeriodDao periodDao;

    public List<Rent> getRentsByRenterId(Long id) {
        Renter renter = this.renterDao.getOne(id);
        if (renter.isAdmin()) {
            return this.rentDao.findAll();
        }
        else {
            return this.rentDao.getRentsByRenterId(id);
        }
    }


    public Rent getOne(Long id) {
        return this.rentDao.getOne(id);
    }

    public Rent saveRent(Rent rent) {
        rent.setClient(this.clientDao.save(rent.getClient()));
        rent.setPeriod(this.periodDao.save(rent.getPeriod()));
        rent.setRenter(this.renterDao.save(rent.getRenter()));
        return this.rentDao.save(rent);
    }

    public void deleteRent(Long id) {
        this.rentDao.delete(id);
    }
}
