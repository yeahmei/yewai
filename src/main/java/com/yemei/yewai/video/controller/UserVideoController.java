package com.yemei.yewai.video.controller;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.FFmpegResult;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.github.kokorin.jaffree.ffmpeg.UrlInput;
import com.github.kokorin.jaffree.ffmpeg.UrlOutput;
import com.sun.xml.internal.ws.api.pipe.Codec;
import com.yemei.yewai.video.VideoUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Paths;

/**
 * ?
 *
 * @author minghua.xie
 * @date 2024/2/4
 **/
public class UserVideoController {



    public void getVideoList() {
        // todo 获取视频列表，可以有搜索条件
    }

    public void getVideoDetail() {
        // todo 获取视频详情
    }

    public void streamVideo1() {
        // todo 播放视频
    }

    @GetMapping("/stream/{fileType}/{fileName}")
    public Mono<ResponseEntity<byte[]>> streamVideo2(ServerHttpResponse serverHttpResponse,
                                                     @RequestHeader(value = "Range", required = false) String httpRangeList,
                                                     @PathVariable("fileType") String fileType,
                                                     @PathVariable("fileName") String fileName) {
        return Mono.just(VideoUtils.prepareContent(fileName, fileType, httpRangeList));
    }


    /**
     * 流服务器格式，rtsp://user:password@192.168.1.123/stream
     */
    static String RTSP_URL = "rtsp://your_rtsp_stream_url/";

    // 例子 http://www.yourserver.com/videos/output01.m3u8
    @GetMapping("/video/{videoId}")
    public ResponseEntity<String> getVideo(@PathVariable String videoId) {
        String videoUrl = RTSP_URL + videoId ;
        return ResponseEntity.ok(videoUrl);
    }
}
