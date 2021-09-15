package com.example.accessingdatamysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long summ;
    @Column(nullable = false)
    private Long term;
    @Column(nullable = false)
    private Long percent;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
    private boolean deleted;
}
