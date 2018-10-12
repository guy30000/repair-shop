package org.launchcode.models.data;
// https://www.youtube.com/watch?v=EeAMVMt2vMU&t=29s p2 view at 0:30 X
import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MenuDao extends CrudRepository<Menu, Integer> { // 0:30 p2 /exentes crudrepo working with menuclass and key is integers


}
