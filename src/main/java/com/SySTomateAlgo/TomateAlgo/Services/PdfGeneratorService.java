package com.SySTomateAlgo.TomateAlgo.Services;

import com.SySTomateAlgo.TomateAlgo.Entities.Order;
import com.SySTomateAlgo.TomateAlgo.Entities.OrderItem;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfGeneratorService {
    @Autowired
    private TemplateEngine templateEngine;


    public byte[] generateOrderPDF(Order order) throws Exception {
        Context context = new Context();
        context.setVariable("event", order.getEvent());

        // Inicializamos las listas por subTipo
        List<OrderItem> alcohol = new ArrayList<>();
        List<OrderItem> nonAlcohol = new ArrayList<>();
        List<OrderItem> equip = new ArrayList<>();
        List<OrderItem> fruit = new ArrayList<>();
        List<OrderItem> ice = new ArrayList<>();
        List<OrderItem> glass = new ArrayList<>();

        // Clasificamos los items
        for (OrderItem item : order.getItems()) {
            String subType = item.getProduct().getSubType();
            if (subType == null) continue;

            switch (subType.toLowerCase()) {
                case "alcohol" -> alcohol.add(item);
                case "sin alcohol", "bebida sin alcohol" -> nonAlcohol.add(item);
                case "barra", "equipamiento", "barra movil" -> equip.add(item);
                case "fruta" -> fruit.add(item);
                case "hielo" -> ice.add(item);
                case "cristaleria" -> glass.add(item);
            }
        }

        // Agregamos todo al contexto
        context.setVariable("alcohol", alcohol);
        context.setVariable("nonAlcohol", nonAlcohol);
        context.setVariable("equip", equip);
        context.setVariable("fruit", fruit);
        context.setVariable("ice", ice);
        context.setVariable("glass", glass);

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
