package com.caseStudy.eCart.Respository;

import com.caseStudy.eCart.modals.Orderhistory;
import com.caseStudy.eCart.modals.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<Orderhistory,Long> {
    List<Orderhistory> findAllByUsers(Users users);
}
