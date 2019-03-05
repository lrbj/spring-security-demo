package com.example.security.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 3:05 PM 3/5/2019
 */
@Data
@Entity
@Table(name="USER")
@ToString(exclude = "roles")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "username_cant_be_null")
    private String username;

    @Column(name = "PASSWORD", length = 100)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE_REL",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;

    public User() {
    }

    public User(Long id, String username, String password,
                String firstname, String lastname, String email,
                Boolean enabled, Date lastPasswordResetDate, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.roles = roles;
    }
}
