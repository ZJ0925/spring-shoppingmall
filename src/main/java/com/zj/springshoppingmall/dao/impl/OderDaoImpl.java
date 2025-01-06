package com.zj.springshoppingmall.dao.impl;

import com.zj.springshoppingmall.dao.OrderDao;
import com.zj.springshoppingmall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer CreateOrder(Integer userId, Integer totalAmount) {
        //如果table名稱有跟資料庫語法重疊，要用``標示(Tab上面那顆)
        String sql = "INSERT INTO `order`(user_id, total_amount, created_date, " +
                "last_modified_date) VALUES (:userId, :totalAmount, :createdDate, " +
                ":lastModifiedDate)";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date date = new Date();
        map.put("createdDate", date);
        map.put("lastModifiedDate", date);
        //儲存自動生成的主鍵值（如自增 OrderID）。
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int orderId = keyHolder.getKey().intValue();
        return orderId;
    }

    @Override
    public void createdOrderItems(Integer orderId, List<OrderItem> orderItemList) {

//        //創建orderItem的變數並開始迭代orderItemList，使用for each效率較慢
//        for(OrderItem orderItem : orderItemList) {
//            String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount) VALUES ( " +
//                    ":orderId, :productId, :quantity, :amount)";
//            Map<String, Object> map = new HashMap<>();
//            //map裡附入orderItemList裡的OrderItem
//            map.put("orderId", orderId);
//            map.put("productId", orderItem.getProductId());
//            map.put("quantity", orderItem.getQuantity());
//            map.put("amount", orderItem.getAmount());
//            namedParameterJdbcTemplate.update(sql, map);
//        }


        String sql = "INSERT INTO order_item(order_id, product_id, quantity, amount) VALUES ( " +
                ":orderId, :productId, :quantity, :amount)";
        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
        for(int i = 0; i < orderItemList.size(); i++) {

            OrderItem orderItem = orderItemList.get(i);

            parameterSources[i] = new MapSqlParameterSource();

            //索引的第i組分別就放入i裡面的東西
            /*
            i=1--> 訂單編號:1  產品ID:A12 數量:3  商品總金額:200
            i=2--> 訂單編號:1  產品ID:B53 數量:2  商品總金額:950
            .
            .
            .
             *///解釋
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());

        }
        //batchUpdated可以執行批量資料庫操作
        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
    }
}
