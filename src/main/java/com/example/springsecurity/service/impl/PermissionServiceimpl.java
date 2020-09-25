package com.example.springsecurity.service.impl;

import com.example.springsecurity.Pojo.Permission;
import com.example.springsecurity.dao.PermissionMapper;
import com.example.springsecurity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceimpl implements PermissionService {
        @Autowired
        private PermissionMapper permissionMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Permission record) {
        return 0;
    }

    @Override
    public int insertSelective(Permission record) {
        return 0;
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return null;
    }


    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return 0;
    }

    @Override
    public List<Permission> findByUserid(int userId) {
        return permissionMapper.findByUserid(userId);
    }

    @Override
    public List<Permission> findall() {
        return permissionMapper.findAll();
    }
}
