package com.ggm.cafemanagement.service.impl;

import com.ggm.cafemanagement.common.exception.AccessDeniedException;
import com.ggm.cafemanagement.common.exception.NotFoundException;
import com.ggm.cafemanagement.domain.dto.CafeTableDto;
import com.ggm.cafemanagement.domain.entity.CafeTable;
import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.domain.enums.RoleEnum;
import com.ggm.cafemanagement.repository.TableRepository;
import com.ggm.cafemanagement.repository.UserRepository;
import com.ggm.cafemanagement.service.TableService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional
    public List<CafeTableDto> findAll() {
        List<CafeTable> cafeTables = tableRepository.findAll();
        return mapper.map(cafeTables, new TypeToken<List<CafeTableDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public List<CafeTableDto> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found", String.format("Could not found user by id %s", userId)));
        return mapper.map(user.getTables(), new TypeToken<List<CafeTableDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public List<CafeTableDto> findAllFree() {
        List<CafeTable> tables = tableRepository.findAll()
                .stream()
                .filter(cafeTable -> cafeTable.getWaiter() == null)
                .collect(Collectors.toList());
        return mapper.map(tables, new TypeToken<List<CafeTableDto>>() {
        }.getType());
    }

    @Override
    @Transactional
    public void save(CafeTableDto cafeTableDto) {
        CafeTable cafeTable = mapper.map(cafeTableDto, CafeTable.class);
        tableRepository.save(cafeTable);
    }

    @Override
    @Transactional
    public void assign(Long tableId, Long userId) {
        CafeTable table = tableRepository.findById(tableId).orElseThrow(
                () -> new NotFoundException("Table not found", String.format("Could not table user by id %s", tableId)));
        if (Objects.nonNull(table.getWaiter())) {
            throw new AccessDeniedException("Access denied to assign table",
                    String.format("Access denied to assign '%s' table to '%s' user. Already assign to %s user", table, userId, table.getWaiter().getId()));
        }

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found", String.format("Could not found user by id %s", userId)));
        if (RoleEnum.WAITER != user.getRole()) {
            throw new AccessDeniedException("Access denied to assign table",
                    String.format("Access denied to assign '%s' table to '%s' user. User is not waiter role", table, userId));
        }

        table.setWaiter(user);
        tableRepository.save(table);
    }

}