package com.alpha.www.Banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.www.Banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
