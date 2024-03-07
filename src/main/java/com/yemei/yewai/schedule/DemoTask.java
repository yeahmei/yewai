package com.yemei.yewai.schedule;


//import com.yemei.yewai.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Description: Spring自动任务demo
 * Author: minghua.xie
 * Date: 2023/6/07/05
 */
@Component
@Slf4j
public class DemoTask implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * cron的配置在yml，统一格式 scheduling.任务类型.任务名称.cron
     */
    @Scheduled(cron = "${scheduling.demo.task1.cron}")
    public void task1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("cronTask 当前时间：" + sdf.format(new Date()));
    }

    public static String stringToHex(String input) {
        StringBuilder hex = new StringBuilder();
        for (char c : input.toCharArray()) {
            hex.append(String.format("%02x", (int) c));
        }
        return "X'" + hex.toString() + "'";
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> map = jdbcTemplate.queryForMap("select * from id_bke_bcf_client_db.undo_log where id = 1");
        System.out.println(map);


//        Map rollback_info = Parser.decode((byte[]) map.get("rollback_info"));
//        Object j = rollback_info.get("json_info");
//        System.out.println(j);
//
//
//        jdbcTemplate.update("INSERT INTO user_collect_tab" +
//                        "(id, cy_code, phone, email, version, creator, create_date, modifier, modify_date, shopee_uid, reference_no, json_info)" +
//                        "VALUES(?, '0.33', 'ccc', 'cc', 0, '', 0, '', 0, '', '', " +
//                        "?" +
////                        "CONVERT( ?  USING utf8mb4)" +
//                        ")"
//                , System.currentTimeMillis(), j);
//
//        System.out.println("insert ok");

    }

    private void insertBlob() {
//        Map<String, Object> map = jdbcTemplate.queryForMap("select * from id_bke_bcf_server_db.user_collect_tab where id = 1704336760416");
//        byte[] info = Parser.encode(map);
//        jdbcTemplate.update("INSERT INTO id_bke_bcf_client_db.undo_log" +
//                        "(branch_id, xid, context, rollback_info, log_status, log_created, log_modified, ext)" +
//                        "VALUES(0, '', '', ?, 0,  '2022-09-28 15:21:45.711000000', '2022-09-28 15:21:45.711000000','xx')"
//                , info);
    }
}
