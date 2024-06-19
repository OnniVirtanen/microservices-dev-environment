package rest

import (
	"example.com/order-service/handler"
	"github.com/gin-gonic/gin"
)

func SetupOrderRouter(router *gin.Engine) {
	orderHandler := handler.NewOrderHandler()

	order := router.Group("v1/order")
	{
		order.GET("/", orderHandler.GetOrders)
		order.POST("/", orderHandler.SaveOrder)
	}
}
