package com.example.demo.mongodb;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * mongodb options
 * Created by david on 17-6-18.
 */
public class BatchUpdateOptions {
	private Query query;
	private Update update;
	//如果upsert=true 找到则修改，没有则追加，upsert为false，找不到时，不追加。
	private boolean upsert = false;
	//如果multi=true，则修改所有符合条件的行，否则只修改第一条符合条件的行。
	private boolean multi = false;
	
	public BatchUpdateOptions() {
		super();
	}
	
	public BatchUpdateOptions(Query query, Update update) {
		super();
		this.query = query;
		this.update = update;
	}
	
	public BatchUpdateOptions(Query query, Update update, boolean upsert,
                              boolean multi) {
		super();
		this.query = query;
		this.update = update;
		this.upsert = upsert;
		this.multi = multi;
	}

	public boolean isUpsert() {
		return upsert;
	}

	public void setUpsert(boolean upsert) {
		this.upsert = upsert;
	}

	public boolean isMulti() {
		return multi;
	}

	public void setMulti(boolean multi) {
		this.multi = multi;
	}

	public Query getQuery() {
		return query;
	}
	
	public void setQuery(Query query) {
		this.query = query;
	}
	
	public Update getUpdate() {
		return update;
	}
	
	public void setUpdate(Update update) {
		this.update = update;
	}
}
