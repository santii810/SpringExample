package com.sgomez.work.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

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
