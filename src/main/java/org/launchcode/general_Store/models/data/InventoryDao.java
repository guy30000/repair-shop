package org.launchcode.general_Store.models.data;
import org.launchcode.general_Store.models.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface InventoryDao extends CrudRepository<Inventory, Integer> {




}
