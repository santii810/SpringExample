package com.sgomez.work.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Timestamp;

@Entity
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    private String login;
    @Column
    private String name;
    @Column
    private String password;

}
