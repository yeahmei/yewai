package com.yemei.yewai.video.controller;

import com.github.kokorin.jaffree.StreamType;
import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.PipeOutput;
import com.github.kokorin.jaffree.ffmpeg.UrlInput;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Paths;

import static com.yemei.yewai.video.controller.UserVideoController.RTSP_URL;

/**
 * ?
 *
 * @author minghua.xie
 * @date 2024/2/5
 **/
public class LiveController {


    // 使用Jaffree库中的FFmpeg类进行RTSP流媒体传输
    // 注意：该方法仅适用于Linux系统
    // 例子 http://www.yourserver.com/videos/output01.m3u8
    @GetMapping(value = "/stream/videos/{vid}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> streamVideo3(String vid) throws IOException {

        PipedInputStream in = new PipedInputStream();
        final PipedOutputStream out = new PipedOutputStream(in);

        PipeOutput.pumpTo(out)
                .setFormat("m3u8")
                .addArguments("-movflags", "frag_keyframe+empty_moov")
                .addArguments("-frag_duration", "5000000")
                .addArguments("-preset", "ultrafast")
                .addArguments("-flags", "+global_header")
                .setCodec(StreamType.VIDEO, "copy");

        UrlInput input = UrlInput.fromUrl(RTSP_URL+vid);
        FFmpeg ffmpeg = FFmpeg.atPath(Paths.get("/usr/local/bin/ffmpeg"))
                .addInput(input);

        // 在新线程中启动 FFmpeg 进程
        new Thread(() -> {
            try {
                ffmpeg.execute();
                out.close();
            } catch (IOException e) {
                // todo 处理异常
            }
        }).start();

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }
}
