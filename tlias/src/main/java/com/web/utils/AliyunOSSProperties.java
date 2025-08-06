package com.web.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("aliyun.oss")
public class AliyunOSSProperties {
    private String endpoint;
    private String region;
    private String bucketName;
}
