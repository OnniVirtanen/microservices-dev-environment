package types

import (
	"github.com/google/uuid"
)

type Order struct {
	ID         uuid.UUID `json:"id"`
	CustomerID string    `json:"customerId"`
	ProductIDs []string  `json:"productIds"`
	Price      Money     `json:"price"`
	Date       string    `json:"date"`
}
