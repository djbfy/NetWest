package com.qiqi.netnest.Utils;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

@Slf4j
public class AddrFromIPUtil {
    public static String addrFromIP(String ip) {
        //本地调试换成src/resources/ip2region.xdb
        String dbPath = "./config/ip2region.xdb";

        byte[] vIndex;
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (IOException e) {
            log.error("failed to load vector index from:{}", dbPath);
            return "未知";
        }
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithVectorIndex(dbPath, vIndex);
        } catch (IOException e) {
            log.error("failed to create vectorIndex cached Searcher with:{}", dbPath);
            return "未知";
        }
        String search = null;
        try {
            search = searcher.search(ip);
        } catch (Exception e) {
            log.error("failed to search :{}", ip);
            log.error(e.getMessage());
        }
        try {
            searcher.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return search;
    }
}
