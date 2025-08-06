package com.web.controller;

import com.web.entity.Result;
import com.web.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    //本地保存
    /*@PostMapping("/upload")
    public Result uploadFile(String name, Integer age, MultipartFile file) throws Exception {
        log.info("接收的数据：{},{},{}", name, age, file);

        // 校验文件名
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || !originalFileName.matches("^.+\\.[a-zA-Z0-9]+$")) {
            return Result.error("非法文件名");
        }

        // 使用 UUID 重命名文件
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + extensionName;
        log.info("生成的文件名：{}", fileName);

        // 使用 Path 安全地构建文件路径
        Path uploadDir = Path.of("D:", "JetBrains_Projects", "idea project", "Web-P2", "tlias", "files");
        Path filePath = uploadDir.resolve(fileName).normalize();

        // 确保文件路径在目标目录内
        if (!filePath.startsWith(uploadDir)) {
            return Result.error("非法文件路径");
        }

        // 保存文件
        file.transferTo(filePath);

        return Result.success();
    }*/

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());

        //将文件存储到OSS
        String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        log.info("文件上传后的url：{}",url);

        return Result.success(url);

    }
}
