package com.example.resena.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="reseña")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resena {

    private long idresena;

}
