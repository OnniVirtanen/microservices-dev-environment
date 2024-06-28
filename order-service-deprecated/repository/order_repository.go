package repository

import (
	"example.com/order-service/types"
)

type OrderRepository interface {
	GetAll() ([]types.Order, error)
	SaveOrder(types.Order) error
}
