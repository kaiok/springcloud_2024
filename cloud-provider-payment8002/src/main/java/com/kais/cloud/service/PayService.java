package com.kais.cloud.service;

import com.kais.cloud.entities.Pay;

import java.util.List;

/**
 * @author kais
 * @date 2024.04.04. 15:57
 */
public interface PayService
{
    public int add(Pay pay);
    public int delete(Integer id);
    public int update(Pay pay);

    public Pay   getById(Integer id);
    public List<Pay> getAll();
}