package app.service;

import app.dao.*;
import app.domain.Bill;
import app.domain.Modification;
import app.domain.Renter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.domain.Rent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@Service
@Transactional
public class RentService {

    private static final int CLEANING_PRICE = 50;
    private final String fromEmail = "fdigne@me.com"; //requires valid gmail id
    private final String password = "Sdfdadvd31!"; // correct password for gmail id
    private final String toEmail = "fdigne@me.com"; //requires valid gmail id


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

    public Rent updateRent(Rent rent, boolean paid, Long userId) {
        Renter user = this.renterDao.findOne(userId);
        if (paid) {
            rent.setPaid(!rent.isPaid());
        }
        this.clientDao.save(rent.getClient());
        this.periodDao.save(rent.getPeriod());
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
        if (! paid){
            this.modificationDao.save(new Modification(user, "Location de "+rent.getClient().getName()+" modifiée", new Date()));
        }
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
        Modification modification = this.modificationDao.save(new Modification(user, "Nouvelle location ajoutée", new Date()));

        this.sendEmail(this.toEmail,"[JLP] : " + modification.getMessage(), modification);

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

    /**
     * Utility method to send simple HTML email
     * @param toEmail
     * @param subject
     */
    public void sendEmail(String toEmail, String subject, Modification modification){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        String body = this.getBodyEmail(modification);
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("fdigne@me.com"));

            msg.setSubject(subject, "UTF-8");
            // Send the actual HTML message, as big as you like
            msg.setContent(
                    body,
                    "text/HTML; charset=UTF-8");
            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            Transport.send(msg);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getBodyEmail(Modification modification) {

        String body = "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    border-collapse: collapse;\n" +
                "    width: 100%;\n" +
                "}\n" +
                "\n" +
                "th, td {\n" +
                "    text-align: left;\n" +
                "    padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {background-color: #f2f2f2;}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>" ;
        body += "Salut soldat !<br/><br/>";

        body += "Il y a eu du nouveau sur les locations de "+modification.getRenter().getName()+"<br/><br/>" ;
        body += "L'action suivante a été effectuée : "+modification.getMessage();
        body += "Bises.<br/>Lord of Pibrac.<br/><br/><br/>";
        return body;
    }
}
