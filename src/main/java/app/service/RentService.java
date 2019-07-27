package app.service;

import app.dao.ClientDao;
import app.dao.PeriodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Rent;

import app.dao.RentDao;

import java.util.List;


@Service
@Transactional
public class RentService {

    @Autowired
    private RentDao rentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private PeriodDao periodDao;

    public List<Rent> getAll() {
        return this.rentDao.findAll();
    }

    public Rent getOne(Long id) {
        return this.rentDao.getOne(id);
    }

    public Rent saveRent(Rent rent) {
        rent.setClient(this.clientDao.save(rent.getClient()));
        rent.setPeriod(this.periodDao.save(rent.getPeriod()));
        return this.rentDao.save(rent);
    }

    public String deleteRent(Long id) {
        this.rentDao.delete(id);
        return "Rent deleted";
    }
}
