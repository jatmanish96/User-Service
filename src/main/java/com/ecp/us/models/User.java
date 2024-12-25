package com.ecp.us.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column(name = "uid", unique = true, updatable = true)
    private String user_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private Boolean active;

    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updateAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
