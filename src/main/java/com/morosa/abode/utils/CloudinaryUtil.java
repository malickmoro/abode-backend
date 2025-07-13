package com.morosa.abode.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryUtil {

    private static final Cloudinary cloudinary;

    static {
        Dotenv dotenv = Dotenv.load();
        cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }

    public String uploadFile(MultipartFile file, String folder, boolean unique, boolean overwrite) {
        String path = "documents/" + folder.trim().replaceAll("[^a-zA-Z0-9_-]", "");
        try {
            Map uploadParams = ObjectUtils.asMap(
                    "folder", path,
                    "use_filename", true,
                    "unique_filename", unique,
                    "overwrite", overwrite
            );

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);
            return (String) uploadResult.get("secure_url");

        } catch (IOException e) {
            throw new RuntimeException("File upload to Cloudinary failed", e);
        }
    }

    public Map getImageDetails(String publicId) {
        try {
            return cloudinary.api().resource(publicId, ObjectUtils.asMap("quality_analysis", true));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Cloudinary image details", e);
        }
    }

    public String transformImage(String publicId, int width, int height) {
        return cloudinary.url()
                .transformation(new Transformation()
                        .crop("pad")
                        .width(width)
                        .height(height)
                        .background("auto:predominant"))
                .imageTag(publicId);
    }
}
