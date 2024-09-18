1. Initinal Commit

2. Create a new Project (product-service) branch (prod-service) PORT : 8081

3. Create a new Project (order-service) branch (order-svc) PORT : 8082

4. Create a new Project (inventory-service) branch (inventory-svc) PORT : 8083

5. branch - service-communication
	So now let us establish communication between our services
	the order service will call the inventory-service.
	We would be using WebClient to fecilate the communication
	Create a new class in side the config package in order-service
	add the webflux dependency to add webclient to the project
	Modify the OrderService class
	modify the Inventory controller to handle multile skucodes
	remove the sku-code pathvariable and replace it with Request param and it would be of list type
	similary modify the inventory service and inventory repository
	call the modified inventorycontroller from the orderservice using webclient
	once code is complete restart both the order service and inventory service
	