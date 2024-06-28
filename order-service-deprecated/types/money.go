package types

import (
	"encoding/json"
	"errors"
	"math"
	"strconv"
)

type Money struct {
	Amount int64
}

func (m Money) ToString() string {
	withDecimals := float64(m.Amount) / 100
	return strconv.FormatFloat(withDecimals, 'f', 2, 64)
}

func (m Money) ToFloat64() float64 {
	return float64(m.Amount) / 100
}

func NewMoneyFromFloat(amount float64) Money {
	cents := int64(math.Round(amount * 100))
	return Money{Amount: cents}
}

func NewMoneyFromInt(amount int64) Money {
	return Money{Amount: amount}
}

func (m *Money) UnmarshalJSON(data []byte) error {
	var floatAmount float64

	// Attempt to unmarshal the data into a float64
	if err := json.Unmarshal(data, &floatAmount); err == nil {
		*m = NewMoneyFromFloat(floatAmount)
		return nil
	}

	// If that fails, attempt to unmarshal the data into a string
	var stringAmount string
	if err := json.Unmarshal(data, &stringAmount); err == nil {
		// Convert string to float
		parsedAmount, err := strconv.ParseFloat(stringAmount, 64)
		if err != nil {
			return err
		}
		*m = NewMoneyFromFloat(parsedAmount)
		return nil
	}

	// If both attempts fail, return an error
	return errors.New("cannot unmarshal to Money")
}

// Implement custom marshaling for Order
func (o Order) MarshalJSON() ([]byte, error) {
	type Alias Order // Use a type alias to prevent infinite recursion

	return json.Marshal(&struct {
		Alias
		Price float64 `json:"price"`
	}{
		Alias: (Alias)(o),
		Price: o.Price.ToFloat64(),
	})
}
