package edu.zhuravlev.telegrambusbot2.services.repository;

import edu.zhuravlev.telegrambusbot2.model.Bus;
import org.springframework.data.repository.CrudRepository;


public interface BusRepository extends CrudRepository<Bus, String> {
}
