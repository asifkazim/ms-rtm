package az.asifkazim.service.impl;

import az.asifkazim.service.FileService;
import az.asifkazim.util.FileUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final MinioClient minioClient;
    private final FileUtil filesUtil;

    @Value("${minio.bucket}")
    private String bucketName;
    private final String TEXT_MEDIA_TYPE = "text";


    @SneakyThrows
    @Override
    public String uploadFile(MultipartFile file, String folder) {
        String fileExtension = filesUtil.getFileExtensionIfAcceptable(file, TEXT_MEDIA_TYPE);
        String fileName = filesUtil.generateUniqueName(fileExtension);
        String objectName = folder + fileName;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(inputStream, inputStream.available(), -1).contentType(file.getContentType()).build());
        return fileName;
    }

    @SneakyThrows
    @Override
    public byte[] getFile(String fileName, String folder) {
        String objectName = folder + fileName;
        GetObjectArgs minioRequest = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
        byte[] bytes = null;
        try {
            bytes = minioClient.getObject(minioRequest).readAllBytes();
        } catch (ErrorResponseException e) {
            ErrorResponse response = e.errorResponse();
//            log.error("Minio error occurred with: {}, {}, {}",
//                    kv("code", response.code()), kv("message", response.message()),
//                    kv("objectName", response.objectName()));
        }
        return bytes;
    }

    @SneakyThrows
    @Override
    public void deleteFile(String fileName, String folder) {
        String objectName = folder + fileName;
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

}
