package org.launchcode.general_Store.models.data;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface InvoicesDao extends CrudRepository<InvoicesDao, Integer>
 {
}
