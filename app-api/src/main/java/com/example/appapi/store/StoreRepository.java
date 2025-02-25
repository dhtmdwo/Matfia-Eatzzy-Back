package com.example.appapi.store;

import com.example.appapi.store.model.AllowedStatus;
import com.example.appapi.store.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findByAllowed(AllowedStatus allowedStatus, PageRequest of);
}
