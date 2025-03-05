package com.example.appapi.menus;

import com.example.appapi.menus.model.Menus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MenusRepository extends JpaRepository<Menus, Long> {
}
