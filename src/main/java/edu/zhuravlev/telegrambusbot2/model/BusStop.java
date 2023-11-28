package edu.zhuravlev.telegrambusbot2.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class BusStop {
    @Id
    private int id;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "BUS_STOP_BUS",
               joinColumns = @JoinColumn(name = "BUS_STOP_ID", referencedColumnName = "ID"),
               inverseJoinColumns = @JoinColumn(name = "BUS_ID", referencedColumnName = "NAME"))
    private Set<Bus> buses;
}
