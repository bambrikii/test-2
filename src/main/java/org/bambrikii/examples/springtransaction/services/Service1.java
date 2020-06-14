package org.bambrikii.examples.springtransaction.services;

import lombok.extern.slf4j.Slf4j;
import org.bambrikii.examples.springtransaction.entities.UserDetails;
import org.bambrikii.examples.springtransaction.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;

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

    private UserDetails createAndSaveUserDetails(String email) {
        UserDetails details = new UserDetails();
        details.setEmail(email);
        userDetailsRepository.save(details);
        return details;
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void transaction2() {
        createAndSaveUserDetails("example2@example.com");
    }

    public void method1() {
        log.info("method1");
    }

    @Autowired
    private TransactionTemplate transactionTemplate;

    public void method3() {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                UserDetails details = createAndSaveUserDetails("example3@example.com");
                return details;
            }
        });
    }

    @Transactional
    public void method4() {
        log.info("method6 - RuntimeException");
        UserDetails details = createAndSaveUserDetails("example4@example.com");
        throw new RuntimeException();
    }

    @Transactional
    public void method5() {
        log.info("method6 - IllegalTransactionStateException");
        UserDetails details = createAndSaveUserDetails("example5@example.com");
        throw new IllegalTransactionStateException("");
    }

    @Transactional
    public void method6() throws IOException {
        log.info("method6 - IOException");
        UserDetails details = createAndSaveUserDetails("example6@example.com");
        throw new IOException();
    }

    @Transactional(dontRollbackOn = RuntimeException.class)
    public void method7() {
        log.info("method7 - dontRollbackOn = RuntimeException");
        UserDetails details = createAndSaveUserDetails("example7@example.com");
        throw new RuntimeException();
    }

    @Transactional(rollbackOn = IOException.class)
    public void method8() throws IOException {
        log.info("method8 - rollbackOn = IOException");
        UserDetails details = createAndSaveUserDetails("example8@example.com");
        throw new IOException();
    }

    @Autowired
    private JpaTransactionManager transactionManager;
    @Autowired
    private EntityManager entityManager;

    public void method9programmatic() {
        log.info("method9 - programmatic");
        TransactionDefinition definition = TransactionDefinition.withDefaults();
        TransactionStatus status = transactionManager.getTransaction(definition);
        try {
            UserDetails details = new UserDetails();
            details.setEmail("example9manual@example.com");
            entityManager.persist(details);
            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
        }
    }
}
