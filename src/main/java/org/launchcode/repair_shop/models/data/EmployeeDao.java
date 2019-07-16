package org.launchcode.repair_shop.models.data;


import org.launchcode.repair_shop.models.forms.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
