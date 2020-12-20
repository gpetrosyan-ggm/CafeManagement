package com.ggm.cafemanagement.service.impl;

import com.ggm.cafemanagement.common.exception.AccessDeniedException;
import com.ggm.cafemanagement.common.exception.NotFoundException;
import com.ggm.cafemanagement.domain.dto.OrderDto;
import com.ggm.cafemanagement.domain.entity.CafeTable;
import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.entity.User;
import com.ggm.cafemanagement.domain.enums.OrderStatusEnum;
import com.ggm.cafemanagement.domain.enums.RoleEnum;
import com.ggm.cafemanagement.repository.OrderRepository;
import com.ggm.cafemanagement.repository.TableRepository;
import com.ggm.cafemanagement.repository.UserRepository;
import com.ggm.cafemanagement.service.OrderService;
import com.ggm.cafemanagement.util.SecurityHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class to store/get/update {@link Order} object.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    /**
     * Finding table's orders by table id.
     *
     * @param tableId the table id.
     * @return list od {@link OrderDto} objects.
     */
    @Override
    @Transactional
    public List<OrderDto> findAllByTableId(Long tableId) {
        CafeTable cafeTable = tableRepository.findById(tableId).orElseThrow(
                () -> new NotFoundException("Table not found", String.format("Could not found table by id %s", tableId)));
        return mapper.map(cafeTable.getOrders(), new TypeToken<List<OrderDto>>() {
        }.getType());
    }

    /**
     * Find order by id.
     *
     * @param orderId the order id.
     * @return {@link OrderDto} object.
     */
    @Override
    @Transactional
    public OrderDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order not found", String.format("Could not found order by id %s", orderId)));
        return mapper.map(order, OrderDto.class);
    }

    // TODO some part of checking can be done by repository level. Needs to investigate Hibernate.

    /**
     * Create table's order.
     * If table has already opened order throw {@link AccessDeniedException} exception.
     * If user is not waiter or table has another waiter throw {@link AccessDeniedException} exception.
     *
     * @param orderDto {@link OrderDto} object going to be stored.
     */
    @Override
    @Transactional
    public void create(OrderDto orderDto) {
        CafeTable cafeTable = tableRepository.findById(orderDto.getTableId()).orElseThrow(
                () -> new NotFoundException("Table not found", String.format("Could not found table by id %s", orderDto.getTableId())));
        if (cafeTable.getOrders().stream().map(Order::getStatus).collect(Collectors.toList()).contains(OrderStatusEnum.OPEN)) {
            throw new AccessDeniedException("Access denied to create order",
                    String.format("Access denied to create order on '%s' table. There is a open order.", cafeTable.getId()));
        }

        String userName = SecurityHelper.retrieveUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Could not found user by user name %s", userName)));
        if (RoleEnum.WAITER != user.getRole() || Objects.isNull(cafeTable.getWaiter()) || !cafeTable.getWaiter().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access denied to create order",
                    String.format("Access denied to assign order to '%s' user", user.getId()));
        }

        Order order = mapper.map(orderDto, Order.class);
        order.setStatus(OrderStatusEnum.OPEN);
        order.setTable(cafeTable);
        order.setWaiter(user);
        orderRepository.save(order);
    }

    /**
     * Update table's order.
     * If user is not waiter or table has another waiter throw {@link AccessDeniedException} exception.
     * If updated order status is OPEN and table has already OPEN order throw {@link AccessDeniedException} exception.
     *
     * @param orderDto {@link OrderDto} object going to be updated.
     */
    @Override
    @Transactional
    public void update(OrderDto orderDto) {
        Order orderDb = orderRepository.findById(orderDto.getId()).orElseThrow(
                () -> new NotFoundException("Order not found", String.format("Could not found order by id %s", orderDto.getId())));

        String userName = SecurityHelper.retrieveUserName();
        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Could not found user by user name %s", userName)));
        if (RoleEnum.WAITER != user.getRole() || Objects.isNull(orderDb.getWaiter()) || !orderDb.getWaiter().getId().equals(user.getId())) {
            throw new AccessDeniedException("Access denied to update order",
                    String.format("Access denied to update order to '%s' user", user.getId()));
        }

        boolean hasOpen = orderRepository.findAll()
                .stream().anyMatch(order -> order.getTable().getId().equals(orderDb.getTable().getId()) && OrderStatusEnum.OPEN == order.getStatus());

        if (OrderStatusEnum.OPEN == orderDto.getStatus() && hasOpen) {
            throw new AccessDeniedException("Access denied to edit order",
                    String.format("Access denied to edit order '%s'. Table already has open order.", orderDto.getId()));
        }


        Order order = mapper.map(orderDto, Order.class);
        orderRepository.update(order);
    }

}
