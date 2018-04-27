package com.example.demo.solr;

/**
 * data build interface.
 */
public interface ISolrDocumentBuild {

    /**
     * build {@link Object} type to {@link T}
     *
     * @param entity object entity
     * @return {@link T} object
     * @throws IllegalAccessException build failed error
     */
    <T> void build(Object entity, T t) throws IllegalAccessException;

    /**
     * build {@link S} type to {@link E} entity.
     *
     * @param s   {@link S} type
     * @param e   {@link E} class
     * @param <S> {@link S} type
     * @param <E> {@link E} type
     * @return {@link E} object
     * @throws NoSuchFieldException   build failed error
     * @throws IllegalAccessException build failed error
     */
    <S, E> void objToEntity(S s, E e) throws NoSuchFieldException, IllegalAccessException;
}
