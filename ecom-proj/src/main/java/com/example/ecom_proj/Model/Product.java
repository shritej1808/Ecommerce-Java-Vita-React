package com.example.ecom_proj.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "Product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String brand;


    @JsonFormat(shape = JsonFormat.Shape.STRING) // Optional formatting for BigDecimal
    private BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String category;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date release_date;

    private boolean available;  // No need for JsonFormat here

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private int quantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String imageName;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String imageType;
    @Lob
    private byte[] imageData;
    @JsonProperty("imageData")
    public String getImageDateBase64() {
        return imageData != null ? Base64.getEncoder().encodeToString(imageData) : null;
    }
}
