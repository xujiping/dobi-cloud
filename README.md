# 微服务项目

## 注册中心 
1. 阿里云服务器注册中心：http://144.34.143.83:8848/nacos

## 接口文档  
1. 宠物萌主：http://localhost:8081/doc.html  http://47.110.251.3:8081/doc.html  


## 部署流程  
1. docker build -t dobi-pets .  
2. docker tag dobi-pets xjp1070860185/dobi-pets  
3. docker push xjp1070860185/dobi-pets  
4. 登陆portainer，更新service  


## 网关中心  
> http://ip:8110  
目前没有统一权限认证，由各子系统校验token  


## 聚合swagger文档  
> http://ip:8110/doc.html  






