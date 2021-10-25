package tacos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.entity.Order;
import tacos.entity.User;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	List<Order> findByZip(String deliveryZip);
	
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}