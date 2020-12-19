package com.ggm.cafemanagement.service;

import com.ggm.cafemanagement.domain.dto.CafeTableDto;

import java.util.List;

public interface TableService {

    List<CafeTableDto> findAll();

    List<CafeTableDto> findAllByUserId(Long userId);

    List<CafeTableDto> findAllFree();

    void save(CafeTableDto cafeTableDto);

    void assign(Long tableId, Long userId);

}
