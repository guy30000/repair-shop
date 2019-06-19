package org.launchcode.repair_shop.models.data;

import org.launchcode.repair_shop.models.forms.NewPeople;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PeopleDao extends CrudRepository <NewPeople, Integer> {


}
