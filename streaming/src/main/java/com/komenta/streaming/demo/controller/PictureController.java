package com.komenta.streaming.demo.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public byte[] getImageWithMediaType(@PathVariable("fileName") String fileName) throws IOException {
        String path = Paths.get(System.getProperty("user.dir")).getFileSystem().getRootDirectories().iterator().next().toString();
        path += "home/ubuntu/Picture/Poster/";
        InputStream in = getClass().getResourceAsStream(path+fileName+".jpg");
        return IOUtils.toByteArray(in);
    }

}
