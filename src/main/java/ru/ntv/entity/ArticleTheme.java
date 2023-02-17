//package ru.smart_transportation.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Table(name = "article_theme")
//public class ArticleTheme {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "theme_id", referencedColumnName = "id")
//    private Theme theme;
//
//    @ManyToOne
//    @JoinColumn(name = "article_id", referencedColumnName = "id")
//    private Article article;
//
//}
