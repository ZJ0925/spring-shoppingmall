package com.zj.springshoppingmall.rowmapper;

import com.zj.springshoppingmall.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemRowMapper implements RowMapper<OrderItem> {
    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();

        orderItem.setOderitemId(rs.getInt("order_item_id"));
        orderItem.setOrderId(rs.getInt("order_id"));
        orderItem.setProductId(rs.getInt("product_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setAmount(rs.getInt("amount"));

        //將Product類別新增進來，也需要再model新增
        orderItem.setProductName(rs.getString("product_name"));
        orderItem.setImageUrl(rs.getString("image_url"));

        return orderItem;
    }
}
