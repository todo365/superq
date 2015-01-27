/**
 * 
 */
package com.zhaoping.framework.mongodb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.zhaoping.framework.serialization.BytesUtil;
import com.zhaoping.framework.serialization.JsonUtil;

/**
 * @author hongxiao.shou
 * @Description:mongdb操作
 * @Copyright: Copyright(c)2004-2014
 * @Company:联嘉云集团有限公司
 */

public class MongoQuey<E extends Serializable> {

	private static Logger logger = Logger.getLogger(MongoQuey.class);
	private BasicDBObject query = new BasicDBObject();
	private BasicDBObject backbBasicDBObject = new BasicDBObject();
	private BasicDBObject sortBasicDBObject = new BasicDBObject();

	private int page;
	private int pageCount;
	private DB db = null;
	private DBCollection collection;

	private MongoConfig config;

	private MongoElemnt elemnt;

	
	public void setConfig(MongoConfig config) {
		this.config = config;
	}

	
	public void setElemnt(MongoElemnt elemnt) {
		this.elemnt = elemnt;
	}

	
	public void InitMongodb() {
		String key = this.config.getKey();
		elemnt = config.getMongoList().get(key);
		ServerAddress serverAddr;
		try {
			serverAddr = new ServerAddress(elemnt.getAddr(), elemnt.getPort());
			MongoClientOptions opt = new MongoClientOptions.Builder()
					.connectionsPerHost(config.getMaxconnetion())
					.threadsAllowedToBlockForConnectionMultiplier(
							config.getMaxthreadqueue())
					.connectTimeout(config.getConnecttimeout())
					.maxConnectionLifeTime(config.getMaxconnectionlifetime())
					.maxWaitTime(config.getMaxwaittime())
					.socketTimeout(config.getSockettimeout()).build();

			MongoClient mongoClient = new MongoClient(serverAddr, opt);
			db = mongoClient.getDB(config.getDataBase());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public MongoQuey SelectCollection(String collectionName) {
		collection = db.getCollection(collectionName);
		return this;
	}

	/**
	 * 插入一个实体数据
	 * 
	 * @param model
	 *            实体类
	 * @return 是否成功 1成功
	 */
	public synchronized int insert(E model) {
		DBObject insertDbObject = new BasicDBObject();
		int rt = -1;
		try {
			db.requestStart();
			String jString = JsonUtil.toString(model);
			insertDbObject.putAll((Map) JsonUtil.toObject(jString));
			WriteResult result = collection.insert(insertDbObject);
			rt = result.getN();
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		db.requestDone();
		return rt;
	}

	/**
	 * 查询条件数据
	 * 
	 * @return 查询到的列表数据
	 */
	private List<DBObject> select() {
		DBCursor dbCursor = null;
		db.requestStart();

		ArrayList<DBObject> arrayList = new ArrayList<DBObject>();
		dbCursor = collection.find(query);
		if (sortBasicDBObject.size() != 0)
			dbCursor.sort(sortBasicDBObject);
		if (page > 0 && pageCount > 0) {
			dbCursor.skip((page - 1) * pageCount).limit(pageCount);
		}
		while (dbCursor.hasNext()) {
			DBObject dbObject = dbCursor.next();
			arrayList.add(dbObject);
		}
		db.requestDone();
		return arrayList;
	}

	/**
	 * 查询条件数据
	 * 
	 * @return 查询到的列表数据
	 */
	public <E> List<E> select(Class<E> classz) {
		E object = null;
		List<E> es = new ArrayList<E>();
		List<DBObject> list = select();
		for (DBObject item1 : list) {
			object = getModel(classz, item1);
			es.add(object);
		}
		return es;
	}

	/**
	 * 更新指定条件数据
	 * 
	 * @param key
	 *            更新key
	 * @param obj
	 *            更新的value
	 * @return 是否成功 1成功
	 */

	public int update(String key, E obj) {
		int rt = -1;
		db.requestStart();
		DBObject updatedValue = new BasicDBObject();
		updatedValue.put(key, obj);
		DBObject updateSetValue = new BasicDBObject("$set", updatedValue);
		WriteResult result = collection.update(query, updateSetValue, false,
				true);
		rt = result.getN();
		db.requestDone();
		return rt;
	}

	/**
	 * 删除指定条件数据
	 * 
	 * @return 1已经被删除
	 */
	public int delete() {
		int rt = -1;
		db.requestStart();
		WriteResult result = collection.remove(query);
		rt = result.getN();
		db.requestDone();
		return rt;
	}

	/**
	 * 按条件查询
	 * 
	 * @param key
	 *            指定的key
	 * @param condition
	 *            条件类型
	 * @param obj
	 *            指定的内容
	 * @return
	 */
	public synchronized MongoQuey where(String key, Condition condition,
			Object obj) {

		backbBasicDBObject = new BasicDBObject(condition.getValue(), obj);
		query.put(key, backbBasicDBObject);

		return this;
	}

	/**
	 * 等于条件查询
	 * 
	 * @param key
	 *            指定的key
	 * @param obj
	 *            指定的内容
	 * @return
	 */
	public synchronized MongoQuey where(String key, Object obj) {
		query.put(key, obj);
		return this;
	}

	/**
	 * 并条件查询
	 * 
	 * @param key
	 *            指定的key
	 * @param obj
	 *            指定的内容
	 * @return
	 */
	public synchronized MongoQuey add(Condition condtion, Object obj) {
		if (backbBasicDBObject != null) {
			backbBasicDBObject.put(condtion.getValue(), obj);
		}
		return this;
	}

	/**
	 * 按条件order by
	 * 
	 * @param key
	 *            指定的key
	 * @param byDirection
	 *            升续，还是降
	 * @return
	 */
	@SuppressWarnings("null")
	public synchronized MongoQuey sortby(String key, SortByDirection byDirection) {
		if (key != null || key.length() > 0) {
			sortBasicDBObject.put(key, byDirection.getValue());
		}
		return this;
	}

	/**
	 * 分页查
	 * 
	 * @param pageNum
	 *            页号
	 * @param pageRecord
	 *            每页数量
	 * @return
	 */

	public MongoQuey page(int pageNum, int pageRecord) {
		this.page = pageNum;
		this.pageCount = pageRecord;
		return this;
	}

	@SuppressWarnings("hiding")
	private <E> E getModel(Class<E> classz, DBObject dbObject) {
		E object = null;
		try {
			String jsString = JsonUtil.toString((Map) dbObject);
			object = JsonUtil.toObject(jsString, classz);
		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}
		return object;
	}

}
