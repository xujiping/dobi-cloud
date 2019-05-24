package com.cloud.pets.service.impl;

import com.cloud.base.constants.Constants;
import com.cloud.base.util.FileUtil;
import com.cloud.pets.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xujiping
 * @date 2019-01-28 14:28
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public boolean uploadMultipartFile(MultipartFile file, String fileServer, String folder, String fileName) {
        String filePath = fileServer + Constants.PATH_SEPARATOR + folder;
        try {
            FileUtil.uploadFile(file.getBytes(), fileName, filePath);
            // todo 以后增加上传是文件服务器
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
