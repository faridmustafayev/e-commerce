package com.rustam.e_commerce.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String productName;
    Integer quantity;
    BigDecimal price;
    BigDecimal totalPrice;

    @ManyToOne(fetch = LAZY, cascade = MERGE)
    @JsonBackReference
    Cart cart;

    @ManyToOne(fetch = LAZY, cascade = MERGE)
    @JsonBackReference
    Product product;

    public void calculateTotalPrice() {
        if (this.price != null && this.quantity != null) {
            this.totalPrice = this.price.multiply(new BigDecimal(this.quantity));
        }
    }

}
