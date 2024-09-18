package com.revature.ms.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.revature.ms.order.dto.InventoryResponse;
import com.revature.ms.order.dto.OrderLineItemsDto;
import com.revature.ms.order.dto.OrderRequest;
import com.revature.ms.order.model.Order;
import com.revature.ms.order.model.OrderLineItems;
import com.revature.ms.order.repository.OrderRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;
   
    public void placeOrder(OrderRequest orderRequest) {
    	Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItems);
        
        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        
     // Call Inventory Service, and place order if product is in stock
        InventoryResponse[] inventoryResponseArray=webClient.get()
        		.uri("http://localhost:8083/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
        		.retrieve()
        		.bodyToMono(InventoryResponse[].class)
        		.block();
        
        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);
        
        if(allProductsInStock) {
		orderRepository.save(order);
        }else {
			throw new IllegalArgumentException("Product is not in stock");
		}
       }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}