package com.komenta.streaming.demo.controller;

import com.komenta.streaming.demo.service.VideoStreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/video")
public class VideoStreamController {

    private final VideoStreamService videoStreamService;

    public VideoStreamController(VideoStreamService videoStreamService) {
        this.videoStreamService = videoStreamService;
    }

    @PostMapping("/video_upload")
    public int registVideo(@RequestParam("file") MultipartFile videofile) {
        String video = "video/" + videofile.getOriginalFilename();
//        video.replace(" ", "_");

        File targetFile = new File(video);
        System.out.println(targetFile);

        try {
            InputStream fileStream = videofile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            System.out.println("파일 업로드 성공");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        return 1;
    }

    @GetMapping("/stream/{fileType}/{fileName}")
    public Mono<ResponseEntity<byte[]>> streamVideo(ServerHttpResponse serverHttpResponse, @RequestHeader(value = "Range", required = false) String httpRangeList,
                                                    @PathVariable("fileType") String fileType,
                                                    @PathVariable("fileName") String fileName) {
        return Mono.just(videoStreamService.prepareContent(fileName, fileType, httpRangeList));
    }
}
