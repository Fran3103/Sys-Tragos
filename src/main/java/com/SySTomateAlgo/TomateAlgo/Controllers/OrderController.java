package com.SySTomateAlgo.TomateAlgo.Controllers;

import com.SySTomateAlgo.TomateAlgo.DTOs.OrderDTO;
import com.SySTomateAlgo.TomateAlgo.Entities.Order;
import com.SySTomateAlgo.TomateAlgo.Mapper.OrderMapper;
import com.SySTomateAlgo.TomateAlgo.Services.OrderService;
import com.SySTomateAlgo.TomateAlgo.Services.PdfGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
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
}
