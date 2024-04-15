package com.kais.cloud.service.impl;

import com.kais.cloud.entities.Pay;
import com.kais.cloud.mapper.PayMapper;
import com.kais.cloud.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kais
 * @date 2024.04.04. 15:58
 */
@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public int add(Pay pay){
        return payMapper.insertSelective(pay);
    }
    @Override
    public int delete(Integer id){
        return payMapper.deleteByPrimaryKey(id);
    }
    @Override
    public int update(Pay pay){
        return payMapper.updateByPrimaryKeySelective(pay);
    }
    @Override
    public Pay getById(Integer id){
        return payMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Pay> getAll(){
        return payMapper.selectAll();
    }
}