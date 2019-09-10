# spring_cloud_ribbon
## Ribbon源码修改
### 修改目的:
修改ribbon请求时的服务名称，从而达到切换不同请求服务的目的

### 修改类:
  spring-cloud-openfeign-core jar包下的org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient 类
  
### 修改类容:

#### 增加一个变量开关  
  ![image](https://github.com/lcliucheng/spring_cloud_ribbon/blob/master/images/mock2.png)
  
#### 修改ribbon通过服务名获取主机id  
  ![image](https://github.com/lcliucheng/spring_cloud_ribbon/blob/master/images/mock1.png)
  
### 导入jar

    导入jar可以选择手动导入，也可以将jar放入仓库，从仓库引入

#### 手动导入
    首先修改pom文件中的依赖，spring-cloud-starter-openfeign
    
   ![image](https://github.com/lcliucheng/spring_cloud_ribbon/blob/master/images/mock3.png)
     
    然后将修改的jar 导入至项目中
    
#### 开关配置
      
      可以通过接口或者配置文件，去修改对应isMock的值，就可以达到切换效果
    
    



