package com.zj.springshoppingmall.rowmapper;

import com.zj.springshoppingmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RoWMapper要使用JDB的，並在後面加入<Product>的泛型，表示要轉換成Product這個JAVA class
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        //從model這個package中的Product這個class取得
        Product product = new Product();
        //re.getInt == resultSet.getInt
        //可以取得product這個欄位的數據
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setCategory(rs.getString("category"));
        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreatedDate(rs.getTimestamp("created_date"));
        product.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        //讀取到數據後回傳
        return product;
    }
}
