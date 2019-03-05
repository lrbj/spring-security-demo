package com.example.security.demo.entity;

import com.example.security.demo.consist.RoleName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Kayla, Ye
 * @Description:
 * @Date:Created in 4:00 PM 3/5/2019
 */
@Entity
@Table(name = "ROLE")
@Data
@ToString(exclude = "users")
public class Role {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

}
