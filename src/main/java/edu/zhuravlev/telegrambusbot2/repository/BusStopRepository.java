package edu.zhuravlev.telegrambusbot2.repository;

import edu.zhuravlev.telegrambusbot2.model.BusStop;
import org.springframework.data.repository.CrudRepository;

public interface BusStopRepository extends CrudRepository<BusStop, Integer> {
}
