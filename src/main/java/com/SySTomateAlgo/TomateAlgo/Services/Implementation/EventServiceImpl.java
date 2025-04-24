package com.SySTomateAlgo.TomateAlgo.Services.Implementation;

import com.SySTomateAlgo.TomateAlgo.Entities.Barra;
import com.SySTomateAlgo.TomateAlgo.Entities.Client;
import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import com.SySTomateAlgo.TomateAlgo.Repositories.BarraRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ClientRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.EventRepository;
import com.SySTomateAlgo.TomateAlgo.Repositories.ServiceRepository;
import com.SySTomateAlgo.TomateAlgo.Services.EventService;
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

        return repository.save(event);
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


        exitsEvent.setDate(eventData.getDate());
        exitsEvent.setDetails(eventData.getDetails());
        exitsEvent.setLocation(eventData.getLocation());
        exitsEvent.setInvitaCant(eventData.getInvitaCant());

        if (eventData.getClient() != null) {
            exitsEvent.setClient(eventData.getClient());
        }

        if (eventData.getService() != null) {
            exitsEvent.setService(eventData.getService());
        }

        if (eventData.getBarra() != null) {
            exitsEvent.setBarra(eventData.getBarra());
        }

        return repository.save(exitsEvent);

    }

    @Override
    public void delete(Long id) {

        repository.deleteById(id);



    }
}
