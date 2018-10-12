package org.launchcode.models.data;
// All from p1 unless specified

import org.launchcode.models.Category;  //not sure about this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {  //interface


}
