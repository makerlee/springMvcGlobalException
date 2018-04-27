package com.example.demo.solr;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * data build util.
 *
 * @author TaoNbo
 * @create 2017-05-24 12:39
 **/
@Component
public class SolrDocumentBuild implements ISolrDocumentBuild {

    @Override
    public <T> void build(Object entity, T t) throws IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ((SolrInputDocument) t).addField(field.getName(), field.get(entity));
        }
    }

    @Override
    public <S, E> void objToEntity(S s, E e) throws NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String, Object> entry : ((SolrDocument) s)) {
            String key = entry.getKey();
            if (!key.equals("_version_")) {
                Field field = e.getClass().getDeclaredField(key);
                if (field != null) {
                    field.setAccessible(true);
                    field.set(e, entry.getValue());
                }
            }
        }
    }
}
