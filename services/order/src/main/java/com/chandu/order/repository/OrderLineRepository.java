package com.chandu.order.repository;

import com.chandu.order.domain.OrderLine;
import com.chandu.order.record.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository  extends JpaRepository<OrderLine,Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);
}
