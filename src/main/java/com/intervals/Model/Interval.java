package com.intervals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "model")
@NoArgsConstructor
public class Model {
    @Id
    Long id;
    @Column(name = "f")
    Integer first;
    @Column(name = "s")
    Integer second;
}
