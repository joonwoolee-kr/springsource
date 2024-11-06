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

@DiscriminatorValue("A")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Album extends Item {
    private String artist;
}
