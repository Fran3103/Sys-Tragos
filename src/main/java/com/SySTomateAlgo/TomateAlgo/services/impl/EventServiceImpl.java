package com.SySTomateAlgo.TomateAlgo.services.impl;

import com.SySTomateAlgo.TomateAlgo.entities.Client;
import com.SySTomateAlgo.TomateAlgo.entities.Event;
import com.SySTomateAlgo.TomateAlgo.entities.Barra;
import com.SySTomateAlgo.TomateAlgo.repositories.*;
import com.SySTomateAlgo.TomateAlgo.services.EventService;
import com.SySTomateAlgo.TomateAlgo.services.OrderService;
import com.SySTomateAlgo.TomateAlgo.utils.UpdatePropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    public EventRepository repository;
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public ServiceRepository serviceRepository;
    @Autowired
    public BarraRepository barraRepository;
    @Autowired
    public OrderService orderService;
    @Autowired
    public OrderRepository orderRepository;



    @Override
    public Event save(Event event) {


        if (event.getClient() != null){
            Client client = clientRepository.findById(event.getClient().getId())
                    .orElseThrow(()-> new RuntimeException("Cliente no encotrado"));
            event.setClient(client);
        }

        if (event.getService() != null){
            com.SySTomateAlgo.TomateAlgo.entities.Service service = serviceRepository.findById(event.getService().getId())
                    .orElseThrow(()-> new RuntimeException("Servicio no encotrado"));
            event.setService(service);
        }

        if (event.getBarras() != null && !event.getBarras().isEmpty()) {
            List<Long> stationsIds = event.getBarras().stream().map(Barra::getId).toList();

            List<Barra> barras = barraRepository.findAllById(stationsIds);
            barras.forEach(s -> s.setEvent(event));
            event.setBarras(barras);
        }
        Event savedEvent = repository.save(event);

        orderService.generateOrderFromEvent(savedEvent);

        return savedEvent;
    }





    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Event update(Long id, Event eventData) {
        Event exitsEvent = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Evento no encontrado"));


        UpdatePropertiesUtil.copyNonNullProperties(eventData, exitsEvent);
        return repository.save(exitsEvent);

    }

    @Override
    public void delete(Long id) {
        orderRepository.findByEventId(id)
                .ifPresent(orderRepository::delete);
        repository.deleteById(id);



    }
}
