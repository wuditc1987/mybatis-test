package com.mybatis.config.log;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.alibaba.fastjson.JSON;
import org.slf4j.helpers.MessageFormatter;

import java.util.stream.Stream;

/**
 * @author : wudi
 * @version: 1.0.0
 * @description MybatisObjectMessageConverter
 *  日志打印对象
 * @date: 2019/12/21
 */
public class MybatisObjectMessageConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        try {
            return MessageFormatter.arrayFormat(event.getMessage(), Stream.of(event.getArgumentArray())
                    .map(JSON::toJSONString).toArray()).getMessage();
        } catch (Exception e) {
            return event.getMessage();
        }
    }
}
