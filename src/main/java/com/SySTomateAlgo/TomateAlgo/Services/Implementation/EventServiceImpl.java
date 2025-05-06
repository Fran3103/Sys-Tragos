package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import com.SySTomateAlgo.TomateAlgo.Repositories.*;
import com.SySTomateAlgo.TomateAlgo.Services.EventService;
import com.SySTomateAlgo.TomateAlgo.Services.OrderService;
import com.SySTomateAlgo.TomateAlgo.Utils.UpdatePropertiesUtil;
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

        if (event.getBarra() != null){
            Barra barra = barraRepository.findById(event.getBarra().getId())
                    .orElseThrow(()-> new RuntimeException("Barra no encotrada"));
            event.setBarra(barra);
        }
        if (event.getService() != null){
            com.SySTomateAlgo.TomateAlgo.Entities.Service service = serviceRepository.findById(event.getService().getId())
                    .orElseThrow(()-> new RuntimeException("Servicio no encotrado"));
            event.setService(service);
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
