package com.zj.springshoppingmall.rowmapper;

import com.zj.springshoppingmall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
       //從User這個class去set email和password
        User user = new User();
        //將user的object與資料庫欄位做對應，所以用set
        user.setUserId(rs.getInt("user_Id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCreatedDate(rs.getTimestamp("created_date"));
        user.setLastmodifiedDtae(rs.getTimestamp("last_modified_date"));

        return user;
    }
}
