package edu.zhuravlev.telegrambusbot2.repository;

import edu.zhuravlev.telegrambusbot2.model.Bus;
import org.springframework.data.repository.CrudRepository;


public interface BusRepository extends CrudRepository<Bus, String> {
}
