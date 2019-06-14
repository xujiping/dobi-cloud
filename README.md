# 微服务项目

## 注册中心（占用内存过大，暂时没使用）  
1. 阿里云服务器注册中心：http://47.110.251.3:8848/nacos  nacos  nacos  

## 接口文档  
1. 宠物萌主：http://localhost:8081/doc.html  http://47.110.251.3:8081/doc.html  



## 账号  
1. 数据库：
> root Dobi123456  
> 公网：rm-m5e8r75zz97048wqp3o.mysql.rds.aliyuncs.com  
> 内网：rm-m5e8r75zz97048wqp.mysql.rds.aliyuncs.com  

## 部署流程  
1. docker build -t dobi-pets .  
2. docker tag dobi-pets xjp1070860185/dobi-pets  
3. docker push xjp1070860185/dobi-pets  
4. 登陆portainer，更新service  



