package com.Libri.repositories;

import com.Libri.models.Fine;
import com.Libri.models.Loan;
import com.Libri.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoanRepository extends JpaRepository<Loan, UUID> {

    Loan findByUser(User user);
}
