package edu.zhuravlev.telegrambusbot2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class TelegramUser {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String chatId;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_BUS_STOPS",
            joinColumns =
                    @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
                    @JoinColumn(name = "BUS_STOP_ID", referencedColumnName = "ID")
    )
    private Set<BusStop> userStops;
}
