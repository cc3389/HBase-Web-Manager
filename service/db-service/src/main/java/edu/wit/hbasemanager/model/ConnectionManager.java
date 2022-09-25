package edu.wit.hbasemanager.model;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Component
public class ConnectionManager {
    /**
     * 实例最大数量
     */
    @Value("cache.capacity")
    private static int maxNum;

    /**
     * LRU缓存存放实例防止存放实例过多
     */
    private final static Map<String, Connection> connectionMap = new LRUCache(maxNum);

    /**
     * 创建Hbase连接，获取连接
     * @param name 连接名
     * @param config hbase连接信息配置
     * @return hbase连接
     */
    public static Connection createConnection(String name, Configuration config) {
        try {

            Connection connection = ConnectionFactory.createConnection(config);
            connectionMap.put(name,connection);
            // TODO 校验重复put
            return connection;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     * @param name
     * @return hbase连接
     */
    public static Connection getConnection(String name) {
        return connectionMap.get(name);
    }

    /**
     *  关闭指定的连接
     * @param name 连接名
     * @return
     */
    public static boolean closeConnection(String name) {
        Connection connection = connectionMap.get(name);
        try {
            connection.close();
            connectionMap.remove(name);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}