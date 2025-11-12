/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author leonard
 */
@Entity
@Table(name = "book_table")
public record Book(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @NotBlank(message = "title is required")
        String title,
        @NotBlank(message = "author is required")
        String author,
        @Min(value = 1979, message = "Year must be at least 1979")
        @Max(value = 2025, message = "Year cannot exceed 2025")
        @Column(name = "year_field")
        int year) {

}
