package be.ucll.da.dentravak.repository;

import be.ucll.da.dentravak.model.SandwichOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<SandwichOrder,UUID> {


}
