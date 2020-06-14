package org.bambrikii.examples.springtransaction.repositories;

import org.bambrikii.examples.springtransaction.entities.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {
}
