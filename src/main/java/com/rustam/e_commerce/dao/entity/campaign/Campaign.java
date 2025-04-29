package com.rustam.e_commerce.dao.entity.campaign;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rustam.e_commerce.dao.entity.Product;
import com.rustam.e_commerce.model.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class Campaign {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    Long discountPercentage;

    @Enumerated(STRING)
    Status status;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "campaign", cascade = {PERSIST, MERGE})
    @JsonBackReference
    List<Banner> banners;

    @OneToMany(mappedBy = "campaign", cascade = {PERSIST, MERGE})
    @JsonBackReference
    List<Product> products;
}
