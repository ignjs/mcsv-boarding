package com.eleuthera.mcsv_boarding.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.eleuthera.mcsv_boarding.models.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}