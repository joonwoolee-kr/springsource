package com.example.movie.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = { "movieImages", "reviews" })
@Entity
public class Movie extends BaseEntity {

    // mno (seq)
    // title
    @Id
    @SequenceGenerator(name = "movie_seq_gen", sequenceName = "movie_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq_gen")
    private Long mno;

    @Column(nullable = false)
    private String title;

    // 자식 연관관계 추가(양방향)
    // @Builder.Default
    // @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    // List<MovieImage> movieImages = new ArrayList<>();

    // @Builder.Default
    // @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    // List<Review> reviews = new ArrayList<>();
}
