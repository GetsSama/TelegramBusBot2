package edu.zhuravlev.telegrambusbot2.services.bus_parser;

import busentity.Bus;
import busparser.BusParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusStopParserServiceImpl implements BusStopParserService {
    private final BusParser busParser;

    @Override
    public List<Bus> getBuses(String busStopUrl) {
        return busParser.parse(busStopUrl);
    }
}
