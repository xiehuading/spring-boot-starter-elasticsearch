###springboot 与 elasticsearch集成
####版本要求  
* jdk1.8+  
* springboot2.0+  
* elasticsearch6

####maven配置  
下载该项目打包上传，然后pom引入配置  

<!-- Spring Boot elasticsearch 依赖 -->
<dependency>
    <groupId>org.elasticsearch.springboot</groupId>
    <artifactId>spring-boot-starter-elasticsearch</artifactId>
    <version>1.0.0</version>
</dependency>

####配置  
#elasticsearch 配置  
spring.data.elasticsearch.cluster-name=es-0pp0v3dlq0009fllc  
spring.data.elasticsearch.cluster-nodes=es-0pp0v3dlq0009fllc.elasticsearch.xhd.com:9200  
spring.data.elasticsearch.username=elastic  
spring.data.elasticsearch.password=xhd@520  
