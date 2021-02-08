package com.komenta.streaming.demo.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@RestController
@RequestMapping("/picture")
public class PictureController {

    @GetMapping(
            value = "/poster/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getPosterImage(@PathVariable(name = "fileName") String fileName) throws IOException {
        String path = Paths.get(System.getProperty("user.dir")).getFileSystem().getRootDirectories().iterator().next().toString();
        path += "home/ubuntu/Picture/Poster/";
        System.out.println(path+fileName+".jpg");
        InputStream image = new FileInputStream(path+fileName+".jpg");
        byte[] imageByte = IOUtils.toByteArray(image);
        System.out.println(imageByte);
        image.close();
        return imageByte;
    }
    @GetMapping(
            value = "/profile/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getProfileImage(@PathVariable(name = "fileName") String fileName) throws IOException {
        String path = Paths.get(System.getProperty("user.dir")).getFileSystem().getRootDirectories().iterator().next().toString();
        path += "home/ubuntu/Picture/Profile/";
        System.out.println(path+fileName+".jpg");
        InputStream image = new FileInputStream(path+fileName+".jpg");
        byte[] imageByte = IOUtils.toByteArray(image);
        System.out.println(imageByte);
        image.close();
        return imageByte;
    }
}
