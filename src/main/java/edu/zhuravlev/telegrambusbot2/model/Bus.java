package edu.zhuravlev.telegrambusbot2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Bus {
    @Id
    @NotNull
    private String name;
}
