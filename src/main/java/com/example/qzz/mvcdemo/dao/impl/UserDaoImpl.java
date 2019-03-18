package com.example.qzz.mvcdemo.dao.impl;

import com.example.qzz.mvcdemo.dao.IUserDao;
import com.example.qzz.mvcdemo.dao.meta.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements IUserDao {


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public long add(User user) {
        String sql = "insert into user (`name`, password, email, create_time, update_time) values (:name, :password, :email, :createTime, :updateTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(user), keyHolder);
        long id = keyHolder.getKey().longValue();
        user.setId(id);
        return id;
    }

    @Override
    public User get(long id) {
        String sql = "select * from user where id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id",id);
        Object user = namedParameterJdbcTemplate.queryForObject(sql,sqlParameterSource,new UserRowMapper());
        return (User)user;
    }

    @Override
    public int delete(User user) {
        String sql = "select * from user where id=:id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        return namedParameterJdbcTemplate.update(sql,sqlParameterSource);
    }

    @Override
    public int update(User user) {
        String sql = "update user set name=:name, password=:password, email=:email, create_time=:createTime, update_time=:updateTime where id=:id";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        return namedParameterJdbcTemplate.update(sql,sqlParameterSource);
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return namedParameterJdbcTemplate.query(sql,new UserRowMapper());
    }

    @Override
    public User getByName(String name) {
        String sql = "select * from user where name=:name";
        SqlParameterSource ps = new MapSqlParameterSource("name",name);
        List<User> users = namedParameterJdbcTemplate.query(sql, ps, new UserRowMapper());
        if(users != null && users.size() >= 1){
            return users.get(0);
        }
        return null;
    }


    class UserRowMapper implements RowMapper<User>{

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {

            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setCreateTime(resultSet.getLong("create_time"));
            user.setUpdateTime(resultSet.getLong("update_time"));
            return user;
        }
    }
}
