package com.SySTomateAlgo.TomateAlgo.services;

import com.SySTomateAlgo.TomateAlgo.entities.Order;
import com.SySTomateAlgo.TomateAlgo.entities.OrderItem;
import com.SySTomateAlgo.TomateAlgo.entities.ProductType;
import com.SySTomateAlgo.TomateAlgo.entities.BarraItem;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;


import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PdfGeneratorService {
    @Autowired
    private TemplateEngine templateEngine;


    public byte[] generateOrderPDF(Order order) throws Exception {
        Context context = new Context();
        context.setVariable("event", order.getEvent());
        context.setVariable("order", order);
        double sumItemsOz = order.getItems().stream()
                .mapToDouble(OrderItem::getOunces)
                .sum();
        context.setVariable("totalOunces", sumItemsOz);

        List<BarraItem> flatEquipments = order.getEvent().getBarras().stream().flatMap(station -> station.getEquipments().stream()).toList();
        context.setVariable("equipment", flatEquipments);

        List<BarraItem> herrEquipments = order.getEvent().getBarras().stream().flatMap(station -> station.getEquipments().stream()).filter(eq -> eq.getProduct().getProductType().name().equals("Herramientas")).toList();
        context.setVariable("herramientas", herrEquipments);

        Map<ProductType, List<OrderItem>> itemsByCategory =
                order.getItems().stream()
                        .collect(Collectors.groupingBy(item ->
                                        item.getProduct().getProductType(),
                                () -> new EnumMap<>(ProductType.class),
                                Collectors.toList()
                        ));
        context.setVariable("itemsByCategory", itemsByCategory);


        String htmlContent = templateEngine.process("order-template", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(htmlContent, null);
        builder.toStream(outputStream);
        builder.run();

        return outputStream.toByteArray();
    }


}
