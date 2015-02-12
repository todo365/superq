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
import com.zhaoping.framework.serialization.JsonUtil;

/**
 * @author hongxiao.shou
 * @Description:mongdb操作
 * @Copyright: Copyright(c)2004-2014
 * @Company:联嘉云集团有限公司
 */
@Component
public class CMongoQuey<E extends Serializable> {
	private static Logger logger = Logger.getLogger(CMongoQuey.class);
	DB db = null;
	private static final ThreadLocal threadSession = new ThreadLocal();

	public static MongoSession getMongoSession() {
		MongoSession mongoSession = (MongoSession) threadSession.get();

		try {
			if (mongoSession == null) {
				mongoSession = new MongoSession();
				threadSession.set(mongoSession);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mongoSession;
	}

	private MongoConfig config;

	private MongoElemnt elemnt;

	@Resource
	public void setConfig(MongoConfig config) {
		this.config = config;
	}

	@Resource
	public void setElemnt(MongoElemnt elemnt) {
		this.elemnt = elemnt;
	}

	@PostConstruct
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

	public CMongoQuey SelectCollection(String collectionName) {
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = db.getCollection(collectionName);
		mongoSession.setCollection(collection);
		return this;
	}

	/**
	 * 插入一个实体数据
	 * 
	 * @param model
	 *            实体类
	 * @return 是否成功 1成功
	 */
	public int insert(E model) {
		DBObject insertDbObject = new BasicDBObject();
		int rt = -1;
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		try {
			db.requestStart();
			// 在测入时利用反色自动插入自增id
			// String jString = JsonUtil.toString(model);
			Map map = (Map) JsonUtil.toObject(model);
			insertDbObject.putAll(map);
			WriteResult result = collection.insert(insertDbObject);
			rt = result.getN();
		} catch (Exception e) {
			logger.error(e.getMessage());

		}
		db.requestDone();
		return rt;
	}

	public int maxID() {
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		db.requestStart();
		DBCursor cursor = collection.find().sort(new BasicDBObject("id", -1))
				.limit(1);
		DBObject obj;

		if (cursor.hasNext()) {
			obj = cursor.next();
			return Integer.parseInt(obj.get("id").toString()) + 1;
		}
		db.requestDone();
		return 1;
	}

	public long Count() {
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		BasicDBObject sortBasicDBObject = mongoSession.getSortBasicDBObject();
		BasicDBObject query = mongoSession.getQuery();
		int page = mongoSession.getPage();
		int pageCount = mongoSession.getPageCount();

		db.requestStart();
		long count = collection.getCount(query);
		sortBasicDBObject = new BasicDBObject();

		query = new BasicDBObject();
		mongoSession.setSortBasicDBObject(sortBasicDBObject);
		mongoSession.setQuery(query);
		mongoSession.setPage(0);
		mongoSession.setPageCount(0);
		db.requestDone();
		return count;
	}

	/**
	 * 查询条件数据
	 * 
	 * @return 查询到的列表数据
	 */
	private List<DBObject> select() {
		DBCursor dbCursor = null;
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		BasicDBObject sortBasicDBObject = mongoSession.getSortBasicDBObject();
		BasicDBObject query = mongoSession.getQuery();
		int page = mongoSession.getPage();
		int pageCount = mongoSession.getPageCount();

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
		sortBasicDBObject = new BasicDBObject();

		query = new BasicDBObject();
		mongoSession.setSortBasicDBObject(sortBasicDBObject);
		mongoSession.setQuery(query);
		mongoSession.setPage(0);
		mongoSession.setPageCount(0);
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
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		DBObject updatedValue = new BasicDBObject();
		updatedValue.put(key, obj);
		DBObject updateSetValue = new BasicDBObject("$set", updatedValue);

		WriteResult result = collection.update(query, updateSetValue, false,
				true); // false//如果数据库不存在，是否添加
		// false//false只修改第一个，true如果有多条就不修改
		rt = result.getN();
		query = new BasicDBObject();
		mongoSession.setQuery(query);
		db.requestDone();
		return rt;
	}

	/**
	 * 
	 * @param obj
	 *            可以更新一个实体或一个子文档 数据库不存在，不添加，只更新找到的第一个，不是更新全部
	 * @return
	 */
	public int update(E obj) {
		int rt = -1;
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		DBObject updatedValue = new BasicDBObject();
		// String jString = JsonUtil.toString(obj);
		try {
			Map map = (Map) JsonUtil.toObject(obj);
			// // Map map = (Map) (JsonUtil.toObject(jString));
			// Double double2=null;
			// Double double21=null;
			// Object oj = map.get("mapPoint");
			// if (oj instanceof Map) {
			// Map map2 = (Map) oj;
			// Object e1 = map2.get("mapX");
			// Object e2 = map2.get("mapY");
			// if (e1 instanceof BigDecimal) {
			// double2 = Double.parseDouble(e1.toString());
			// }
			// if (e2 instanceof BigDecimal) {
			// double21 = Double.parseDouble(e2.toString());
			// }
			// Map map3 = new HashMap<String,Double>();
			// map3.put("mapX", double2);
			// map3.put("mapY", double21);
			// map.remove("mapPoint");
			// map.put("mapPoint", map3);
			// // map.put("mapPoint", double1);
			// }
			updatedValue.putAll(map);
			DBObject updateSetValue = new BasicDBObject("$set", updatedValue);
			WriteResult result = collection.update(query, updateSetValue,
					false, false); // false//如果数据库不存在，是否添加
			// false//false只修改第一个，true如果有多条就不修改
			rt = result.getN();
			query = new BasicDBObject();
			mongoSession.setQuery(query);
			db.requestDone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
		return rt;
	}

	/**
	 * db.blog.update({"title":"one blog"}, {"$push":
	 * {"comments":{"content":"word"}}
	 * 
	 * @param obj
	 *            修改或增加子集 如果其操作的键不存在，则创建新的键值 作的键值已经存在，且其值为数组类型，该修改符将为该数组添加新的数组元素
	 * @return
	 */
	public int update(UpdateCondition condition, E obj) {
		int rt = -1;
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		DBObject updatedValue = new BasicDBObject();
		// String jString = JsonUtil.toString(obj);
		Map map = (Map) JsonUtil.toObject(obj);
		updatedValue.putAll(map);

		DBObject updateSetValue = new BasicDBObject(condition.getValue(),
				updatedValue);
		WriteResult result = collection.update(query, updateSetValue);
		query = new BasicDBObject();
		mongoSession.setQuery(query);
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
		MongoSession mongoSession = getMongoSession();
		DBCollection collection = mongoSession.getCollection();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		WriteResult result = collection.remove(query);
		query = new BasicDBObject();
		mongoSession.setQuery(query);
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
	public CMongoQuey where(String key, Condition condition, Object obj) {
		MongoSession mongoSession = getMongoSession();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		// query = new BasicDBObject();
		BasicDBObject backbBasicDBObject = mongoSession.getBackbBasicDBObject();
		if (isJavaClass(obj.getClass()))
			backbBasicDBObject = new BasicDBObject(condition.getValue(), obj);
		else {
			// String jString = JsonUtil.toString(obj);
			Map map = (Map) JsonUtil.toObject(obj);
			DBObject updatedValue = new BasicDBObject();
			updatedValue.putAll(map);
			backbBasicDBObject = new BasicDBObject(condition.getValue(),
					updatedValue);
		}
		query.put(key, backbBasicDBObject);
		mongoSession.setBackbBasicDBObject(backbBasicDBObject);
		mongoSession.setQuery(query);
		db.requestDone();
		return this;
	}

	// public CMongoQuey where(String key, Condition condition, E obj) {
	// MongoSession mongoSession = getMongoSession();
	// BasicDBObject query = mongoSession.getQuery();
	// BasicDBObject backbBasicDBObject = mongoSession.getBackbBasicDBObject();
	// String jString = JsonUtil.toString(obj);
	// DBObject updatedValue = new BasicDBObject();
	// updatedValue.putAll((Map) JsonUtil.toObject(jString));
	// backbBasicDBObject = new BasicDBObject(condition.getValue(),
	// updatedValue);
	// query.put(key, backbBasicDBObject);
	// return this;
	// }

	/**
	 * 等于条件查询
	 * 
	 * @param key
	 *            指定的key
	 * @param obj
	 *            指定的内容
	 * @return
	 */
	public CMongoQuey where(String key, Object obj) {
		MongoSession mongoSession = getMongoSession();
		db.requestStart();
		BasicDBObject query = mongoSession.getQuery();
		// query = new BasicDBObject();
		if (isJavaClass(obj.getClass()))
			query.put(key, obj);
		else {
			String jString = JsonUtil.toString(obj);
			DBObject updatedValue = new BasicDBObject();
			query.put(key, JsonUtil.toObject(jString));
		}
		mongoSession.setQuery(query);
		db.requestDone();
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
	public CMongoQuey add(Condition condtion, Object obj) {
		MongoSession mongoSession = getMongoSession();
		db.requestStart();
		BasicDBObject backbBasicDBObject = mongoSession.getBackbBasicDBObject();
		if (backbBasicDBObject != null) {
			backbBasicDBObject.put(condtion.getValue(), obj);
		}
		db.requestDone();
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
	public CMongoQuey sortby(String key, SortByDirection byDirection) {

		MongoSession mongoSession = getMongoSession();
		db.requestStart();
		BasicDBObject sortBasicDBObject = mongoSession.getSortBasicDBObject();
		if (key != null || key.length() > 0) {
			sortBasicDBObject.put(key, byDirection.getValue());
		}
		db.requestDone();
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

	public CMongoQuey page(int pageNum, int pageRecord) {
		MongoSession mongoSession = getMongoSession();
		db.requestStart();
		mongoSession.setPage(pageNum);
		mongoSession.setPageCount(pageRecord);
		db.requestDone();
		return this;
	}

	@SuppressWarnings("hiding")
	private <E> E getModel(Class<E> classz, DBObject dbObject) {
		E object = null;
		try {
			String jsString = JsonUtil.toString(dbObject);
		
			object = JsonUtil.toObject(jsString, classz);

		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}
		return object;
	}

	/**
	 * true 返回系统类，false 自定义类
	 * 
	 * @param clz
	 * @return
	 */
	public static boolean isJavaClass(Class<?> clz) {
		return clz != null && clz.getClassLoader() == null;
	}

}
