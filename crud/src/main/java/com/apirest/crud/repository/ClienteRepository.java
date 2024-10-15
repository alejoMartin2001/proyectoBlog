package com.apirest.crud.repository;

import com.apirest.crud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Métodos CRUD ya listos
}
