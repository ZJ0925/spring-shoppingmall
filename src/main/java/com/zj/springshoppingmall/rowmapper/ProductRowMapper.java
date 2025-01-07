package com.zj.springshoppingmall.rowmapper;

import com.zj.springshoppingmall.constant.ProductCategory;
import com.zj.springshoppingmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RoWMapper要使用JDBC的，並在後面加入<Product>的泛型，表示要轉換成Product這個JAVA class
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        //從model這個package中的Product這個class取得
        Product product = new Product();
        //re.getInt == resultSet.getInt
        //可以取得SQL中product這個欄位的數據
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        //先設定一個字串可以去接住category的值
        String categoryStr = rs.getString("category");
        //再透過Enum裡valueOf的方法將字串去尋找ProductCategory裡固定的值
        ProductCategory category = ProductCategory.valueOf(categoryStr);
        //先get後set，如果Enum裡的固定值去跟資料庫作比對發現找不到的話就會顯示Error
        product.setCategory(category);
        //product.setCategory(ProductCategory.valueOf( rs.getString("category")));---->上方簡化版

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
