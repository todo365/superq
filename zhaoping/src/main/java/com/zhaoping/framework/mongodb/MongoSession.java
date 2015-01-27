/**
 * 
 */
package com.zhaoping.framework.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

/**
 * @author hongxiao.shou
 *
 */
public class MongoSession {
	private BasicDBObject query = new BasicDBObject();
	private BasicDBObject backbBasicDBObject = new BasicDBObject();
	private BasicDBObject sortBasicDBObject = new BasicDBObject();

	private int page;
	private int pageCount;
	
	private DBCollection collection;

	public BasicDBObject getQuery() {
		return query;
	}

	public void setQuery(BasicDBObject query) {
		this.query = query;
	}

	public BasicDBObject getBackbBasicDBObject() {
		return backbBasicDBObject;
	}

	public void setBackbBasicDBObject(BasicDBObject backbBasicDBObject) {
		this.backbBasicDBObject = backbBasicDBObject;
	}

	public BasicDBObject getSortBasicDBObject() {
		return sortBasicDBObject;
	}

	public void setSortBasicDBObject(BasicDBObject sortBasicDBObject) {
		this.sortBasicDBObject = sortBasicDBObject;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

//	public DB getDb() {
//		return db;
//	}
//
//	public void setDb(DB db) {
//		this.db = db;
//	}

	public DBCollection getCollection() {
		return collection;
	}

	public void setCollection(DBCollection collection) {
		this.collection = collection;
	}

}
