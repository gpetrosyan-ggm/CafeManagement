package com.ggm.cafemanagement.repository;

import com.ggm.cafemanagement.domain.entity.CafeTable;

import java.util.List;
import java.util.Optional;

public interface TableRepository {

    List<CafeTable> findAll();

    Optional<CafeTable> findById(Long id);

    void save(CafeTable cafeTable);

}
