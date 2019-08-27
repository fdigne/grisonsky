package app.service;

import app.dao.*;
import app.domain.Bill;
import app.domain.Modification;
import app.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Rent;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class RentService {

    private static final int CLEANING_PRICE = 50;

    @Autowired
    private RentDao rentDao;

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RenterDao renterDao;

    @Autowired
    private ModificationDao modificationDao;

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

    public Rent updateRent(Rent rent) {
        rent.setPaid(!rent.isPaid());
        Rent savedRent = this.rentDao.save(rent);
        Renter renter = savedRent.getRenter();
        Bill bill = renter.getBill();
        Double oldService = this.rentDao.getServiceSumByRenterId(rent.getRenter().getId(), new Date());
        Double service = ((oldService != null ? oldService : 0))*0.1;
        int cleaning = (this.rentDao.getCleaningCountByRenterId(rent.getRenter().getId(), new Date()))*CLEANING_PRICE;
        bill.setService(service);
        bill.setCleaning(cleaning);
        renter.setBill(bill);
        this.renterDao.save(renter);
        return savedRent;
    }

    public Rent saveRent(Rent rent, Long userId) {

        rent.setClient(this.clientDao.save(rent.getClient()));
        rent.setPeriod(this.periodDao.save(rent.getPeriod()));
        rent.setRenter(this.renterDao.save(rent.getRenter()));
        Renter user = this.renterDao.findOne(userId);
        Bill bill = user.getBill();
        Rent savedRent = this.rentDao.save(rent);
        Double oldService = this.rentDao.getServiceSumByRenterId(rent.getRenter().getId(), new Date());
        Double service = ((oldService != null ? oldService : 0))*0.1;
        int cleaning = (this.rentDao.getCleaningCountByRenterId(rent.getRenter().getId(), new Date()))*CLEANING_PRICE;
        bill.setService(service);
        bill.setCleaning(cleaning);
        user.setBill(bill);
        this.renterDao.save(user);
        this.modificationDao.save(new Modification(user, "Nouvelle location ajoutée", new Date()));
        return savedRent;
    }

    public void deleteRent(Long rentId, Long userId) {
        Renter user = this.renterDao.findOne(userId);
        Rent rent = this.rentDao.findOne(rentId);
        Renter renter = rent.getRenter();
        Bill bill = renter.getBill();
        this.modificationDao.save(new Modification(user, "Location supprimée", new Date()));
        this.rentDao.delete(rentId);
        Double oldService = this.rentDao.getServiceSumByRenterId(rent.getRenter().getId(), new Date());
        Double service = ((oldService != null ? oldService : 0))*0.1;
        int cleaning = (this.rentDao.getCleaningCountByRenterId(rent.getRenter().getId(), new Date()))*CLEANING_PRICE;
        bill.setService(service);
        bill.setCleaning(cleaning);
        renter.setBill(bill);
        this.renterDao.save(renter);
    }

    public Modification getLastModification() {
        return this.modificationDao.findLastModification();
    }
}
