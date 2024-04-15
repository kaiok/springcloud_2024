package com.kais.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.kais.cloud.apis.PayFeignApi;
import com.kais.cloud.entities.PayDTO;
import com.kais.cloud.resp.ResultData;
import com.kais.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author kais
 * @date 2024.04.05. 23:28
 */
@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        System.out.println("第一步：模拟本地addOrder新增订单成功(省略sql操作)，第二步：再开启addPay支付微服务远程调用");
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");

        ResultData resultData = null;
        try{
            System.out.println("--调用开始：" + DateUtil.now());
             resultData = payFeignApi.getPayInfo(id);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("--调用结束：" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return resultData;
    }

    @GetMapping(value = "feign/pay/info")
    public String myLoadBalance(){
        return payFeignApi.myLoadBalance();
    }
}
