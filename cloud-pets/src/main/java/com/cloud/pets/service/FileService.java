package com.cloud.pets.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务类
 * @author xujiping
 * @date 2019-01-28 14:27
 */
public interface FileService {

    /**
     * 多文件上传
     * @param file
     * @param fileServer
     * @param folder
     * @param fileName
     * @return
     */
    boolean uploadMultipartFile(MultipartFile file, String fileServer, String folder, String fileName);
}
