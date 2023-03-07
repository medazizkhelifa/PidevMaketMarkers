package tn.esprit.spring.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.entities.GiftPoint;

public interface IGiftPointRepository extends JpaRepository<GiftPoint, Long> {
    @Query(
        value="select sum(gifts.point) as total, (sum(gifts.point) - (select SUM(i.discount) from invoices i where i.order_id in (select id from orders where client_id =?1))) as available_balance, gifts.client_id as client_id from gift_points gifts  where gifts.client_id=?1",
        nativeQuery = true
    )
    Map<String, Object> getAvailableBalance(Long client_id);

    //i.order_id in (SELECT id from orders where client_id=?1)
}
