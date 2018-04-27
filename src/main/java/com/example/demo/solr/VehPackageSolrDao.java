package com.example.demo.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * vehicle gateway package solr data aop object impl.
 *
 * @author TaoNbo
 * @create 2017-05-24 12:36
 **/
@Component
public class VehPackageSolrDao implements IVehPackageSolrDao {

    private static final Logger logger = LoggerFactory.getLogger(VehPackageSolrDao.class);

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SolrDocumentBuild solrDocumentBuild;

    /**
     * Adding debug data is success count.
     */
    private long debugDataAddSuccessCount = 0;

    @Override
    public <T> void save(T entity) {
        this.save(null, entity);
    }

    @Override
    public <T> void save(String collection, T entity) {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        try {
            solrDocumentBuild.build(entity, solrInputDocument);
        } catch (IllegalAccessException e) {
            logger.error("build solr input document object error.");
            return;
        }
        try {
            UpdateResponse addResponse = solrClient.add(collection, solrInputDocument);
            if (addResponse.getStatus() == 0) {
                try {
                    UpdateResponse commitResponse = solrClient.commit(collection);
                    String STATUS = "FAILED";
                    if (commitResponse.getStatus() == 0) {
                        STATUS = "SUCCEED";
                        this.debugDataAddSuccessCount++;
                    }
                    logger.info("Add document to solr repertories state: {}", STATUS);
                } catch (SolrServerException | IOException e) {
                    logger.error("solr client commit solr input document failed.");
                }
            } else {
                logger.error("Add document to solr repertories state: {}", addResponse.getStatus());
            }
        } catch (SolrServerException | IOException e) {
            logger.error("solr client add solr input document failed.");
            e.printStackTrace();
        }
    }

    @Override
    public <T> void delete(String coreName, String id) {
        try {
            UpdateResponse addResponse = solrClient.deleteByQuery(coreName,"id:"+id);
            if (addResponse.getStatus() == 0) {
                try {
                    UpdateResponse commitResponse = solrClient.commit(coreName);
                    String STATUS = "FAILED";
                    if (commitResponse.getStatus() == 0) {
                        STATUS = "SUCCEED";
                    }
                    logger.info("删除文档成功 state: {}", STATUS);
                } catch (SolrServerException | IOException e) {
                    logger.error("solr client commit solr input document failed.");
                }
            } else {
                logger.error("删除文档失败 state: {}", addResponse.getStatus());
            }
        } catch (SolrServerException | IOException e) {
            logger.error("执行删除失败");
            e.printStackTrace();
        }
    }

    @Override
    public <T> void save(List<T> entities) {
        this.save(null, entities);
    }

    @Override
    public <T> void save(String collection, List<T> entities) {
        Collection<SolrInputDocument> docs = new ArrayList<>();
        for (T entity : entities) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            try {
                solrDocumentBuild.build(entity, solrInputDocument);
            } catch (IllegalAccessException e) {
                logger.error("build solr input documents object error.");
                return;
            }
            docs.add(solrInputDocument);
        }
        try {
            UpdateResponse response = solrClient.add(collection, docs);
            if (response.getStatus() == 0) {
                UpdateResponse commit = solrClient.commit(collection);
                String STATUS = "FAILED";
                if (commit.getStatus() == 0) {
                    STATUS = "SUCCEED";
                    this.debugDataAddSuccessCount += docs.size();
                }
                logger.info("Add documents to solr repertories state: {}", STATUS);
            } else {
                logger.error("Add document to solr repertories state: {}", response.getStatus());
            }
        } catch (SolrServerException | IOException e) {
            logger.error("solr client add solr input documents failed.");
            e.printStackTrace();
        }
    }

    /**
    @Override
    public List<DebugEntity> selectPackages(ISearchQuery query, Page page) throws IllegalAccessException, RuntimeException {
        return this.selectPackages(null, query, page);
    }

    @Override
    public List<DebugEntity> selectPackages(String collection, ISearchQuery query, Page page) throws IllegalAccessException, RuntimeException {
        StringBuilder queryStr = new StringBuilder("*:*");
        if (StringUtils.isNotBlank(query.getVin())) {
            queryStr.append(" AND vin:").append(query.getVin());
        }
        if (StringUtils.isNotBlank(query.getSource())) {
            queryStr.append(" AND source:").append(query.getSource());
        }
        if (query.getStartDate() != null) {
            if (query.getEndDate() != null) {
                queryStr.append(" AND sendTimestamp:[").append(query.getStartDate().getTime()).append(" TO ").append(query.getEndDate().getTime()).append("]");
            } else {
                queryStr.append(" AND sendTimestamp:[").append(query.getStartDate().getTime()).append(" TO ").append(System.currentTimeMillis()).append("}");
            }
        } else {
            if (query.getEndDate() != null) {
                queryStr.append(" AND sendTimestamp:{* TO ").append(query.getEndDate().getTime()).append("]");
            }
        }
        if (StringUtils.isNotBlank(query.getKeyWords())) {
            queryStr.append(" AND codecPayloadIndex:").append(query.getKeyWords().replace(":", "\\:"));
        }
        logger.info("select solr q:{}", queryStr.toString());

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryStr.toString());
        solrQuery.addSort("sendTimestamp", SolrQuery.ORDER.desc);
        solrQuery.setStart((int) ((page.getPageIndex() - 1) * page.getPageSize()));
        solrQuery.setRows((int) page.getPageSize());
        logger.info("select solr sq:{}", solrQuery.toString());

        List<DebugEntity> list = new ArrayList<>();
        try {
            QueryResponse response = solrClient.query(collection, solrQuery);
            logger.info("select solr result:{}", response.getResults());
            SolrDocumentList solrDocumentList = response.getResults();
            for (SolrDocument doc : solrDocumentList) {
                VehComboPackage entity = new VehComboPackage();
                solrDocumentBuild.objToEntity(doc, entity);
                list.add(entity);
            }
            page.setTotalCount(solrDocumentList.getNumFound());
        } catch (Exception e) {
            logger.error("Search alert solr index error!", e);
            throw new RuntimeException("Search alert solr index error!", e.getCause());
        }
        return list;
    }

    */
}
