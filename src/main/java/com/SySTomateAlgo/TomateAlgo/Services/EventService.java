package com.SySTomateAlgo.TomateAlgo.Services;


import com.SySTomateAlgo.TomateAlgo.Entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event save(Event event);
    List<Event> findAll();
    Optional<Event> findById(Long id);
    Event update(Long id, Event event);
    void delete(Long id);
}
