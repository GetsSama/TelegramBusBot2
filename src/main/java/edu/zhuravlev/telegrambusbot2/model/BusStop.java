package edu.zhuravlev.telegrambusbot2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class BusStop {
    @Id
    @NotNull
    private int id;

    @NotNull
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BUS_STOP_BUS",
               joinColumns = @JoinColumn(name = "BUS_STOP_ID", referencedColumnName = "ID"),
               inverseJoinColumns = @JoinColumn(name = "BUS_ID", referencedColumnName = "NAME"))
    private Set<Bus> buses;
}
