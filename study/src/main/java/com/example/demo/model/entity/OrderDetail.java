package com.example.demo.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Data
@Entity //order_detail
@ToString(exclude = {"item","orderGroup"})
@EntityListeners(AuditingEntityListener.class)
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy // 자동으로 LoginUserAuditorAware에 있는 값으로 설정됨
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy // 자동으로 LoginUserAuditorAware에 있는 값으로 설정됨
    private String updatedBy;


    //OrderDetail N:1 Item
    @ManyToOne
    private Item item;

    //OrderDetail N:1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;  //장바구니에 어떠한 상품있는가

}
