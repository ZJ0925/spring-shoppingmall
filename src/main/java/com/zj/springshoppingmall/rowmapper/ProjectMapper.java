package com.zj.springshoppingmall.rowmapper;

import com.zj.springshoppingmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//RoWMapper要使用JDB的，並在後面加入<Product>的泛型，表示要轉換成Product這個JAVA class
public class ProjectMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
