package edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.selection;

import edu.kit.VorhersagenverwaltungSTA.model.requestManager.encoder.Encoder;
import edu.kit.VorhersagenverwaltungSTA.model.requestManager.selection.Order;

public class OrderEncoder implements Encoder<Order> {
    private static final String ORDER_BY_FORMAT = "$orderBy=%s %s";
    @Override
    public String encode(Order order) {
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