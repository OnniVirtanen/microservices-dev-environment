# microservices-dev-and-test-env

This documents consists of a development and a test environment for microservices.

## Order workflow
Let's use choreography based saga pattern for order workflow. Lets use Kafka as a message bus:

![image](https://github.com/OnniVirtanen/microservices-dev-environment/assets/116679314/946b4084-b050-4f5b-adfa-b3ebb7bf80bd)

Events:
OrderCreated
ProductsReserved
PaymentExecuted
ShipmentInformed
OrderCompleted
OrderFailed (for failure scenarios)
Service Responsibilities and Event Handlers:

Order Service:

Action: Create a new order.
Emit Event: OrderCreated
Listen for Events: None (this is the starting point).
Inventory Service:

Listen for Event: OrderCreated
Action: Reserve products.
Emit Event: ProductsReserved (if successful) or OrderFailed (if failed).
Payment Service:

Listen for Event: ProductsReserved
Action: Execute payment.
Emit Event: PaymentExecuted (if successful) or OrderFailed (if failed).
Shipping Service:

Listen for Event: PaymentExecuted
Action: Inform shipment.
Emit Event: ShipmentInformed (if successful) or OrderFailed (if failed).
Order Service:

Listen for Event: ShipmentInformed
Action: Complete the order.
Emit Event: OrderCompleted.
Event Flow:

Order Creation:
When the Order Service creates an order, it emits an OrderCreated event.
Product Reservation:
The Inventory Service listens for OrderCreated, reserves products, and emits ProductsReserved.
Payment Execution:
The Payment Service listens for ProductsReserved, processes the payment, and emits PaymentExecuted.
Shipping Information:
The Shipping Service listens for PaymentExecuted, informs the shipment, and emits ShipmentInformed.
Order Completion:
The Order Service listens for ShipmentInformed and completes the order by emitting OrderCompleted.


## local dev environment:

![image](https://github.com/OnniVirtanen/microservices-dev-environment/assets/116679314/7bf2d40f-95de-413b-92a2-0c1f445d99a3)




## test environment:

![image](https://github.com/OnniVirtanen/microservices-dev-environment/assets/116679314/9574c69a-d0f9-4f79-a2b0-ad71c02bb7e0)



