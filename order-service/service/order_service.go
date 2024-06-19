package service

import (
	"example.com/order-service/infrastructure"
	"example.com/order-service/repository"
	"example.com/order-service/types"
	"github.com/google/uuid"
)

type OrderService struct {
	orderRepository repository.OrderRepository
}

func NewOrderService() *OrderService {
	var orderService = OrderService{}
	orderService.orderRepository = infrastructure.NewOrderMemoryRepository()
	return &orderService
}

func (os *OrderService) GetOrders() ([]types.Order, error) {
	orders, _ := os.orderRepository.GetAll()

	if orders == nil || len(orders) == 0 {
		return []types.Order{}, nil
	}

	return orders, nil
}

func (os *OrderService) SaveOrder(order types.Order) (types.Order, error) {
	order.ID = uuid.New()
	err := os.orderRepository.SaveOrder(order)
	return order, err
}
