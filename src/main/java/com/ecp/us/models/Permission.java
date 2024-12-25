package com.ecp.us.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
