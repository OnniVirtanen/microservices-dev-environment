package handler

import (
	"net/http"

	"example.com/order-service/service"
	"example.com/order-service/types"
	"github.com/gin-gonic/gin"
)

type OrderHandler struct {
	orderService service.OrderService
}

func NewOrderHandler() *OrderHandler {
	var orderHandler = OrderHandler{}
	orderHandler.orderService = *service.NewOrderService()
	return &orderHandler
}

func (oh *OrderHandler) GetOrders(c *gin.Context) {
	var orders, _ = oh.orderService.GetOrders()
	c.IndentedJSON(http.StatusOK, orders)
}

func (oh *OrderHandler) SaveOrder(c *gin.Context) {
	var order types.Order

	if err := c.BindJSON(&order); err != nil {
		c.AbortWithError(http.StatusBadRequest, err)
		return
	}

	order, err := oh.orderService.SaveOrder(order)

	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.JSON(http.StatusAccepted, order)
}
