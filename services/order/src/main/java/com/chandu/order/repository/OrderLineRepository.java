package com.chandu.order.repository;

import com.chandu.order.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository  extends JpaRepository<OrderLine,Integer> {
}
