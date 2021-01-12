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
@Entity
@ToString(exclude = {"user","orderDetail"})
@EntityListeners(AuditingEntityListener.class)
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; //주문의 형태 - 일괄 / 개별

    private String revAddress;

    private String revName;

    private String paymentType;

    private BigDecimal totalprice;

    private Integer totalQuantity;

    private LocalDateTime orderAt  ;

    private LocalDateTime arrivalDate ;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy // 자동으로 LoginUserAuditorAware에 있는 값으로 설정됨
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy // 자동으로 LoginUserAuditorAware에 있는 값으로 설정됨
    private LocalDateTime updatedBy;


    // OrdeGroup N:1 user
    @ManyToOne
    private User user;

    // OrderGroup 1:N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;




}
