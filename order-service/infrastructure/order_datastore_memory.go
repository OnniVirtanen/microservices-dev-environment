package infrastructure

import (
	"fmt"
	"sync"

	"example.com/order-service/types"
	"github.com/google/uuid"
)

type OrderMemoryRepository struct {
	Orders map[uuid.UUID]types.Order
	sync.Mutex
}

func NewOrderMemoryRepository() *OrderMemoryRepository {
	return &OrderMemoryRepository{
		Orders: make(map[uuid.UUID]types.Order),
	}
}

func (omr *OrderMemoryRepository) GetAll() ([]types.Order, error) {
	omr.Lock()
	defer omr.Unlock()

	var orders []types.Order
	for _, order := range omr.Orders {
		orders = append(orders, order)
	}

	return orders, nil
}

func (omr *OrderMemoryRepository) SaveOrder(order types.Order) error {
	omr.Lock()
	defer omr.Unlock()

	id := order.ID
	if _, ok := omr.Orders[id]; ok {
		return fmt.Errorf("order already exists by order id")
	}

	omr.Orders[id] = order
	return nil
}
