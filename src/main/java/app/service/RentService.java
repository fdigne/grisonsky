package app.service;

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

    public List<Rent> getAll() {
        return this.rentDao.findAll();
    }
}
