package edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.service.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.service.requestManager.selection.Order;

public class OrderEncoder implements Encoder<Order> {
    private static final String ORDER_BY_FORMAT = "$orderBy=%s %s";
    @Override
    public String encode(Order order) {
        if (order == null) return null;
        return String.format(ORDER_BY_FORMAT, order.getOrderBy(),
                new OrderDirectionEncoder().encode(order.getOrderDirection()));
    }

    private static class OrderDirectionEncoder implements Encoder<Order.OrderDirection> {

        @Override
        public String encode(Order.OrderDirection orderDirection) {
            String encodedString = "";
            switch (orderDirection) {
                case ASC -> encodedString = "asc";
                case DESC -> encodedString = "desc";
            }
            return encodedString;
        }
    }
}