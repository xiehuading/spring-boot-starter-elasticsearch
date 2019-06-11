package org.elasticsearch.springboot;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lease.Releasable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
public class ElasticSearchAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchAutoConfiguration.class);

    @Autowired
    private ElasticSearchProperties properties;

    @Bean(name = "restClient")
    public RestClient restClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));
        RestClient restClient = RestClient.builder(new HttpHost(properties.getClusterNodes().split(":")[0],
                Integer.parseInt(properties.getClusterNodes().split(":")[1])))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).build();
        return restClient;
    }


    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient highLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(properties.getClusterNodes().split(":")[0],
                Integer.parseInt(properties.getClusterNodes().split(":")[1])));
        return new RestHighLevelClient(restClientBuilder);
    }

}
