package com.bluesky.mybatis.service.impl;

import com.bluesky.mybatis.dao.cluster.CityDao;
import com.bluesky.mybatis.dao.master.UserDao;
import com.bluesky.mybatis.domain.City;
import com.bluesky.mybatis.domain.User;
import com.bluesky.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源

    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        City city = cityDao.findByName("北京");
        user.setCity(city);
        return user;
    }
}
