package com.example.demo.solr;

import java.util.List;

//Data interaction interface.
public interface IVehPackageSolrDao {

    /**
     * Adds a single entity.
     *
     * @param entity object data
     */
    <T> void save(T entity);

    /**
     * Adds a single entity.
     *
     * @param collection the Solr collection to add the document to
     * @param entity     the solr collection to add the entity to
     * @param <T>        the solr collection object type
     */
    <T> void save(String collection, T entity);

    /**
     * delete single entity by id
     * @param coreName
     * @param ida
     * @param <T>
     */
    <T> void delete(String coreName,String id);


    /**
     * Save multiple objects in batches.
     *
     * @param entities objects data
     */
    <T> void save(List<T> entities);

    /**
     * Adds a single entity in List.
     *
     * @param collection the Solr collection to add the document to
     * @param entities   the solr collection to add the entity in list to
     * @param <T>        the solr collection object type
     */
    <T> void save(String collection, List<T> entities);

    /**
     * Paging condition query.
     *
     * @param query Query content
     * @param page  page object
     * @return query result
     * @throws IllegalAccessException {@link IllegalAccessException}
     * @throws RuntimeException       {@link RuntimeException}
     */
    //List<DebugEntity> selectPackages(ISearchQuery query, Page page) throws IllegalAccessException, RuntimeException;

    /**
     * Paging condition query.
     *
     * @param collection the Solr collection to add the document to
     * @param query      the solr query object
     * @param page       the solr query page
     * @return query result
     * @throws IllegalAccessException {@link IllegalAccessException}
     * @throws RuntimeException       {@link RuntimeException}
     */
    //List<DebugEntity> selectPackages(String collection, ISearchQuery query, Page page) throws IllegalAccessException, RuntimeException;
}
