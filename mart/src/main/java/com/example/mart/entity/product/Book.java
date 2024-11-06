package com.example.mart.entity.product;

import com.example.mart.entity.item.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@DiscriminatorValue("B")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Book extends Item {
    private String author;
    private String isbn;
}
