package edu.zhuravlev.telegrambusbot2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bus {
    @Id
    private String name;
}
