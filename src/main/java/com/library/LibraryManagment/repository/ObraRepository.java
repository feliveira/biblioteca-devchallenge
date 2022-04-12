package com.library.LibraryManagment.repository;

import com.library.LibraryManagment.entity.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObraRepository extends JpaRepository<Obra, Integer> {
}
