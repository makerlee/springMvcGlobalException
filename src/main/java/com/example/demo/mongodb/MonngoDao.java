package com.example.demo.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * Created by lijiyang on 2017/10/17.
 */
public interface MonngoDao {
    public void update();

    public int batchUpdate(MongoTemplate mongoTemplate, String collName,
                           List<BatchUpdateOptions> options);
    /**
     * 批量更新
     * @param ordered 如果为true,一条语句更新失败，剩下的语句将不再执。
     *                如果为false,一条语句更新失败，剩下的将继续执行。
     *                默认为true。
     * @return
     */
    public int batchUpdate(MongoTemplate mongoTemplate, String collName,
                           List<BatchUpdateOptions> options,boolean ordered);
}
