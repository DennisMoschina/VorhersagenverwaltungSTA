package edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection;

public class Order {
    private String orderBy;
    private OrderDirection orderDirection;

    public Order(String orderBy, OrderDirection orderDirection) {
        this.orderBy = orderBy;
        this.orderDirection = orderDirection;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public OrderDirection getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(OrderDirection orderDirection) {
        this.orderDirection = orderDirection;
    }

    public enum OrderDirection {
        ASC,
        DESC
    }
}