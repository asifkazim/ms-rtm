package az.asifkazim.util;

import az.asifkazim.error.ExtensionNotAcceptableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class FileUtil {
    @Value("${file.upload.acceptableTextExtensions}")
    private String[] acceptableTextExtensions;

    private boolean isExtensionAcceptable(String extension, String mediaType) {
        if (mediaType.equals("text")) {
            for (String s : acceptableTextExtensions) {
                if (s.equalsIgnoreCase(extension)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getFileExtensionIfAcceptable(@NotNull MultipartFile file, String mediaType) {
        String extension = file.getContentType().split("[/]")[1];
        if (isExtensionAcceptable(extension, mediaType)) {
            return extension;
        } else {
            throw new ExtensionNotAcceptableException(extension);
        }
    }

    public String generateUniqueName(String extension) {
        Date date = new Date();
        return date.getTime() + "." + extension;
    }

}
