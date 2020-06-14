package org.bambrikii.examples.springtransaction.services;

import lombok.extern.slf4j.Slf4j;
import org.bambrikii.examples.springtransaction.entities.UserDetails;
import org.bambrikii.examples.springtransaction.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class Service1 {
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private Service1 service1;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void transaction1() {
        log.info("transaction1");
        createAndSaveUserDetails("example1@example.com");
        service1.transaction2();
//        transaction2();
    }

    private void createAndSaveUserDetails(String email) {
        UserDetails details = new UserDetails();
        details.setEmail(email);
        userDetailsRepository.save(details);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void transaction2() {
        createAndSaveUserDetails("example2@example.com");
    }

    public void method1() {
        log.info("method1");
    }
}
