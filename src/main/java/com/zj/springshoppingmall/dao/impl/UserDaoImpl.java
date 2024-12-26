package com.zj.springshoppingmall.dao.impl;

import com.zj.springshoppingmall.DataTransferObject.UserRegisterRequest;
import com.zj.springshoppingmall.dao.UserDao;
import com.zj.springshoppingmall.model.User;
import com.zj.springshoppingmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired //把Bean注入
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate; //可以實作資料庫語法


    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT user_id , email, password, created_date, last_modified_date " +
                "FROM user WHERE user_id= :uesrId";
        Map<String, Object> map = new HashMap<>();
        map.put("uesrId", userId);
        //使用rowmapper將資料庫的結果轉換成userList
        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());
        if(userList != null){
            return userList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO user(email, password, created_date, last_modified_date) VALUES " +
                "(:email, :password, :createdDate, :lastmodifiedDate)";
        Map<String, Object> map = new HashMap<>();
        //將product_name的這個key對應到productReques中取得ProductName，key值必須與model這個class裡的User的object名稱一樣
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastmodifiedDate", now);
        //用於存儲由資料庫自動生成的主鍵值（如自增 ID）
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //執行SQL語法，將 map 封裝成 MapSqlParameterSource 類型
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        //獲取插入成功後，資料庫生成的主鍵值並轉為整數
        int useId = keyHolder.getKey().intValue();
        return useId;
    }


}
