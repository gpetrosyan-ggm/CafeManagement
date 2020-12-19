package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.TableStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cafe_tables")
public class CafeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "table_name", nullable = false)
    private String tableName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TableStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User waiter;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<Order> orders;

}
