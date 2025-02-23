package com.chen.picturebackend;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xlj
 * @date 2025-02-13
 * @description 测试concurrent-hashmap
 */

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        // 测试concurrent-hashmap是否可以存储null值(不可以为null值)
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("a", null);
        System.out.printf("测试开始=====测试值是否可以为null", map.entrySet().size());
    }
}
