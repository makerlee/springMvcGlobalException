package com.example.demo.mongodb.impl;

import com.example.demo.mongodb.BatchUpdateOptions;
import com.example.demo.mongodb.MonngoDao;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 17-6-18.
 */
@Repository
public class MongoBaseDaoImpl implements MonngoDao {
    @Override
    public void update() {

    }
    @Override
    public int batchUpdate(MongoTemplate mongoTemplate, String collName,
                           List<BatchUpdateOptions> options) {
        return doBatchUpdate(mongoTemplate.getCollection(collName), collName, options, false);

    }
    @Override
    public int batchUpdate(MongoTemplate mongoTemplate, String collName,
                           List<BatchUpdateOptions> options, boolean ordered) {
        return doBatchUpdate(mongoTemplate.getCollection(collName), collName, options, ordered);
    }

    private static int doBatchUpdate(DBCollection dbCollection, String collName,
                                     List<BatchUpdateOptions> options, boolean ordered) {
        DBObject command = new BasicDBObject();
        command.put("update", collName);
        List<BasicDBObject> updateList = new ArrayList<BasicDBObject>();
        for (BatchUpdateOptions option : options) {
            BasicDBObject update = new BasicDBObject();
            update.put("q", option.getQuery().getQueryObject());
            update.put("u", option.getUpdate().getUpdateObject());
            update.put("upsert", option.isUpsert());
            update.put("multi", option.isMulti());
            updateList.add(update);
        }
        command.put("updates", updateList);
        command.put("ordered", ordered);
        CommandResult commandResult = dbCollection.getDB().command(command);
        return Integer.parseInt(commandResult.get("n").toString());
    }

}
