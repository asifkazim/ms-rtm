package az.asifkazim.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(MultipartFile file, String folder);

    byte[] getFile(String fileName, String folder);

    void deleteFile(String fileName, String folder);
}
