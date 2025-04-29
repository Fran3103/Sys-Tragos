package com.SySTomateAlgo.TomateAlgo.Repositories;

import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByServiceId(Long serviceId);
}
