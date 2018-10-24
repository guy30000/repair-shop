package org.launchcode.models.data;

import org.launchcode.models.Cheese;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
//this is how it inderacts with the database https://www.youtube.com/watch?time_continue=2&v=8JI2vH3GwSg 6:50
@Repository
@Transactional
public interface CheeseDao extends CrudRepository<Cheese, Integer> {
}
