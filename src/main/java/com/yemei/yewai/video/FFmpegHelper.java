package com.yemei.yewai.video;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
/**
 * ?
 *
 * @author minghua.xie
 * @date 2024/2/5
 **/
public class FFmpegHelper {

    public static void convertToHLS(String inputFilePath, String outputDirectory) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                "ffmpeg",
                "-i", inputFilePath, // 输入文件路径
                "-codec: copy", // 保持原始编码
                "-start_number", "0", // TS 文件起始编号
                "-hls_time", "10", // 每个 TS 文件的时长（秒）
                "-hls_list_size", "0", // HLS 播放列表大小，0 表示不限制
                "-f", "hls", // 格式为 HLS
                Paths.get(outputDirectory, "output.m3u8").toString() // 输出路径
        );

        Process process = builder.start();
        boolean finished = process.waitFor(60, TimeUnit.MINUTES); // 等待最多 60 分钟

        if (!finished) {
            throw new RuntimeException("FFmpeg process didn't finish within the time limit");
        }

        if (process.exitValue() != 0) {
            throw new RuntimeException("FFmpeg process failed");
        }
    }
}
