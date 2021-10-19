package tacos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	List<Order> findByZip(String deliveryZip);
	
}
