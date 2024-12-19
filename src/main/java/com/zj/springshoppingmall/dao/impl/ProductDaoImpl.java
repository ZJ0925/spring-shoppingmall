package com.zj.springshoppingmall.dao.impl;

import com.zj.springshoppingmall.dao.ProductDao;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    //一定要透過依賴注入!!!
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer id) {
        //執行SQL語法
        String sql = "SELECT product_id,product_name,category,image_url,price,stock,description,created_date,last_modified_date " +
                "FROM product WHERE product_id = :product_id";
        Map<String, Object> map = new HashMap<>();
        //map裡面放入Product的ID
        map.put("product_id", id);
        //簡化資料庫操作
        // namedParameterJdbcTemplate.query(SQl語法, map的key與value, 將結果集中每一行的資料轉換成對應的Java物件)
        //透過List的方式接住query方法的返回值
        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0){//如果list裡面有東西就回傳list裡面的第一個元素
            return productList.get(0);
        }else{
            return null;
        }
    }
}

