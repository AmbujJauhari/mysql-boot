package com.ambuj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Aj on 22-06-2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Starter.class)
public class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void findAllUsers() {
        List users = tradeRepository.findAll();
        System.out.println(users);
        assertNotNull(users);
        assertTrue(!users.isEmpty());
    }

}