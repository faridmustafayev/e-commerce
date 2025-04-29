package com.rustam.e_commerce.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rustam.e_commerce.dao.entity.campaign.Campaign;
import com.rustam.e_commerce.dao.entity.user.BaseUser;
import com.rustam.e_commerce.model.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "products")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String productName;

    String description;

    Integer quantity;

    BigDecimal price;

    BigDecimal discount;

    BigDecimal specialPrice;

    String imageUrl;

    String videoUrl;

    @Enumerated(STRING)
    Status status;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @ManyToOne(fetch = LAZY, cascade = MERGE)
    @JsonBackReference
    Category category;

    @ManyToOne(fetch = LAZY, cascade = MERGE)
    @JsonBackReference
    BaseUser user;

    @ManyToOne(fetch = LAZY, cascade = MERGE)
    @JsonBackReference
    Campaign campaign;

    @OneToMany(mappedBy = "product", cascade = {PERSIST, MERGE})
    @JsonBackReference
    List<CartItem> products;

    @OneToMany(mappedBy = "product", cascade = {PERSIST, MERGE})
    @JsonBackReference
    List<OrderItem> orderItems;
}