package com.ambuj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aj on 22-06-2017.
 */
@Repository
public class TradeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List findAll() {

        List result = jdbcTemplate.queryForList(
                "SELECT * FROM trade_mock");

        return result;

    }
}
