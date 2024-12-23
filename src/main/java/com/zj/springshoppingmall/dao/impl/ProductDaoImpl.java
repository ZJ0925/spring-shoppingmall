package com.zj.springshoppingmall.dao.impl;

import com.zj.springshoppingmall.DataTransferObject.ProductRequest;
import com.zj.springshoppingmall.constant.ProductCategory;
import com.zj.springshoppingmall.dao.ProductDao;
import com.zj.springshoppingmall.model.Product;
import com.zj.springshoppingmall.rowmapper.ProductRowMapper;
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
public class ProductDaoImpl implements ProductDao {
    //一定要透過依賴注入!!!
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductCategory category, String search) {
        /*!!1=1!!是為了要拼接category的sql語句，因為WHERE 1=1 + AND product_category = :category才會有效
        而不是WHERE AND product_category = :category這種無效的語句
         */
        String productsSql = "SELECT product_id, product_name, category, image_url, price, stock" +
                ", description, created_date, last_modified_date FROM product WHERE 1=1";
        Map<String, Object> map = new HashMap<>();

        //如果輸入的分類不等於空值
        if(category != null) {
            //在原來的sql語法加入篩選分類的sql語法(AND前面要空格，才不會如果category不是null會連句在一起)
            productsSql = productsSql+ " AND product_category = :category";
            //並把分類的關鍵字加入到map裡(由於category為Enum類型，不是String，所以要透過name()來將Enum轉換為字串)
            map.put("category", category.name());
        }

        if(search != null) {
            // AND product_name LIKE :search意思為"以及product_name跟search有匹配的商品都顯示出來"
            productsSql = productsSql+ " AND product_name LIKE :search";
            //%關鍵字%為只要裡面有關鍵字就顯示，而"關鍵字%"表示開頭是關鍵字的才會顯示
            //模糊查詢一定要寫在map的值裡面才會生效
            map.put("search","%" + search + "%");
        }
        //執行SQL語法,map以及ProductRowMapper將每一行商品數據顯示出來
        List<Product> productList = namedParameterJdbcTemplate.query(productsSql, map, new ProductRowMapper());
        return productList;
    }

    @Override
    public Product getProductById(Integer id) {
        //執行SQL語法
        String getSql = "SELECT product_id,product_name,category,image_url,price,stock,description,created_date,last_modified_date " +
                "FROM product WHERE product_id = :product_id";
        Map<String, Object> map = new HashMap<>();
        //map裡面放入Product的ID
        map.put("product_id", id);
        //簡化資料庫操作
        // namedParameterJdbcTemplate.query(SQl語法, map的key與value, 將結果集中每一行的資料轉換成對應的Java物件)
        //透過List的方式接住query方法的返回值
        List<Product> productList = namedParameterJdbcTemplate.query(getSql, map, new ProductRowMapper());

        if(productList.size() > 0){//如果list裡面有東西就回傳list裡面的第一個元素
            return productList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        //SQL中的":"表示對應在JAVA中的參數
        String createSql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date) VALUES (:productName, :category," +
                ":imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate);";
        Map<String, Object> map = new HashMap<>();
        //將product_name的這個key對應到productReques中取得ProductName，key值必須與model這個class裡的product的object名稱一樣
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        Date now = new Date();
        //將now這個變數賦予給created_date與last_modified_date
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);
        //用於存儲由資料庫自動生成的主鍵值（如自增 ID）。
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //執行SQL語法，將 map 封裝成 MapSqlParameterSource 類型
        namedParameterJdbcTemplate.update(createSql, new MapSqlParameterSource(map), keyHolder);
        //獲取插入成功後，資料庫生成的主鍵值並轉為整數
        int productId = keyHolder.getKey().intValue();
        //回傳產品ID
        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String updateSql = "UPDATE product SET product_name = :productName, category = :category, " +
                "image_url = :imageUrl, price = :price, stock = :stock, description = :description," +
                " last_modified_date = :lastModifiedDate WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());
        map.put("lastModifiedDate", new Date());
        //執行SQL語法，將 map 封裝成 MapSqlParameterSource 類型
        namedParameterJdbcTemplate.update(updateSql, map);
    }

    @Override
    public void deleteProduct(Integer productId) {
        //從product表格中刪除叫product_id的那個商品
        String deleteSql = "DELETE FROM product WHERE product_id = :productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        //執行sql語法
        namedParameterJdbcTemplate.update(deleteSql, map);
    }
}

