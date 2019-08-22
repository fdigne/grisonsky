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

    public Rent saveRent(Rent rent, Long userId) {

        rent.setClient(this.clientDao.save(rent.getClient()));
        rent.setPeriod(this.periodDao.save(rent.getPeriod()));
        rent.setRenter(this.renterDao.save(rent.getRenter()));
        Renter user = this.renterDao.findOne(userId);
        Double oldService = this.rentDao.getServiceSumByRenterId(rent.getRenter().getId());
        Double service = ((oldService != null ? oldService : 0) + rent.getPrice())*0.1;
        int cleaning = (this.rentDao.getCleaningCountByRenterId(rent.getRenter().getId()) + (rent.isCleaning()?1:0))*CLEANING_PRICE;
        rent.getRenter().getBill().setService(service);
        rent.getRenter().getBill().setCleaning(cleaning);
        this.modificationDao.save(new Modification(user, "Nouvelle location ajoutée", new Date()));
        return this.rentDao.save(rent);
    }

    public void deleteRent(Long rentId, Long userId) {
        Renter renter = this.renterDao.findOne(userId);
        Rent rent = this.rentDao.findOne(rentId);
        Double oldService = this.rentDao.getServiceSumByRenterId(rent.getRenter().getId());
        Double service = ((oldService != null ? oldService : 0) - rent.getPrice())*0.1;
        int cleaning = (this.rentDao.getCleaningCountByRenterId(rent.getRenter().getId()) - (rent.isCleaning()?1:0))*CLEANING_PRICE;
        rent.getRenter().getBill().setService(service);
        rent.getRenter().getBill().setCleaning(cleaning);
        this.modificationDao.save(new Modification(renter, "Location supprimée", new Date()));
        this.rentDao.delete(rentId);
    }

    public Modification getLastModification() {
        return this.modificationDao.findLastModification();
    }
}
