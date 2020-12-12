package com.wipro.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Page<Account> findByCustId(Long custId, Pageable pageable);

	Optional<Account> findByIdAndCustId(Long id, Long custId);

	Optional<Account> findById(Long id);

	List<Account> findAll();
}