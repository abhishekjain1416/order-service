package com.programmingtechie.orderservice.implementation;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.programmingtechie.orderservice.enums.OrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.dto.OrderResponse;
import com.programmingtechie.orderservice.event.OrderPlacedEvent;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import com.programmingtechie.orderservice.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService {

	@Autowired
    public OrderRepository orderRepository;

    @Autowired
    public WebClient.Builder webClientBuilder;

    @Autowired
    private KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        // Map OrderLineItemsDto to OrderLineItems and collect them into a list
        List<OrderLineItems> orderLineItems = orderRequest.getOrderList()
            .stream()
            .map(this::mapToDto)
            .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
            .stream()
            .map(OrderLineItems::getSkuCode)
            .toList();

        // Call Inventory Service to check if product is in stock
        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory",
                uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();

        // Check if all products are in stock
        Boolean allProductsInStock = Arrays.stream(inventoryResponsesArray)
            .allMatch(InventoryResponse::getIsInStock);

        OrderResponse response = new OrderResponse();

        // If all products are in stock, save the order and send a notification
        if(allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));

            response.setMessageCode(OrderEnum.ORDER_PLACED_SUCCESSFULLY.getSuccessCode());
            response.setMessage(OrderEnum.ORDER_PLACED_SUCCESSFULLY.getMessage());
        }
        else {
        	response.setMessageCode(OrderEnum.PRODUCTS_OUT_OF_STOCK.getErrorCode());
            response.setMessage(OrderEnum.PRODUCTS_OUT_OF_STOCK.getMessage());
        }

        return response;
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
