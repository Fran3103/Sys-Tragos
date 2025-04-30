package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.OrderDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Event;
import com.SySTomateAlgo.TomateAlgo.Entities.Order;
import com.SySTomateAlgo.TomateAlgo.Mapper.OrderMapper;
import com.SySTomateAlgo.TomateAlgo.Services.EventService;
import com.SySTomateAlgo.TomateAlgo.Services.OrderService;
import com.SySTomateAlgo.TomateAlgo.Services.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> dtos = orders.stream().map(OrderMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {
       Order order = orderService.findById(id).orElseThrow(()-> new RuntimeException("no se encontro la orde"));
       return ResponseEntity.ok(OrderMapper.toDto(order));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id) throws Exception {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        byte[] pdfBytes = pdfGeneratorService.generateOrderPDF(order);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("orden_" + id + ".pdf").build());


        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @PostMapping("{eventId}/recalculate")
    public  ResponseEntity<OrderDTO> recalculate(@PathVariable Long eventId){
        Event event = eventService.findById(eventId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encotrado"));
        Order order = orderService.generateOrderFromEvent(event);
        return ResponseEntity.ok(OrderMapper.toDto(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        String msg = orderService.delete(id);
        return ResponseEntity.ok(msg); // o ResponseEntity.noContent().build()
    }

}
