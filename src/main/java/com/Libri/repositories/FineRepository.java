package com.Libri.repositories;

import com.Libri.models.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FineRepository extends JpaRepository<Fine, UUID> {
}
