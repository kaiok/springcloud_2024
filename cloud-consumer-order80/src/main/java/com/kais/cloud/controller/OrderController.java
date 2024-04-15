package com.kais.cloud.controller;

import com.kais.cloud.entities.PayDTO;
import com.kais.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author kais
 * @date 2024.04.04. 22:58
 */
@RestController
public class OrderController {

//    public static final String PaymentSrv_URL = "http://192.168.101.22:8001";

    // 使用consul注册支付服务后，使用服务注册中心上的微服务名称
    public static final String PaymentSrv_URL = "http://cloud-payment-service";


    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO,ResultData.class);
    }

    @GetMapping(value = "/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id,ResultData.class,id);
    }

    @GetMapping("consumer/pay/get/info")
    private String getPayInfoByConsul(){
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info",String.class);
    }


    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 遍历consul中所有服务
     * @return
     */
    @GetMapping("/consumer/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }
        System.out.println("===================================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }
}
