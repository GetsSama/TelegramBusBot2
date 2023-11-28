package edu.zhuravlev.telegrambusbot2.services.bus_parser;

import busentity.Bus;

import java.util.List;

public interface BusStopParserService {
    List<Bus> getBuses(String busStopUrl);
}
