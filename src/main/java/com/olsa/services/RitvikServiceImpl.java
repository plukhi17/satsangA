package com.olsa.services;

import org.apache.log4j.Logger;

import com.olsa.bo.MongoBaseDao;
import com.olsa.bo.RitvikMDBDao;
import com.olsa.pojo.ResultObject;

public class RitvikServiceImpl implements RitvikService{
	static final Logger logger = Logger.getLogger(RitvikServiceImpl.class);
	private MongoBaseDao mongoBaseDao;
	private RitvikMDBDao ritvikMDBDao;
	
	public MongoBaseDao getMongoBaseDao() {
		return mongoBaseDao;
	}

	public void setMongoBaseDao(MongoBaseDao mongoBaseDao) {
		this.mongoBaseDao = mongoBaseDao;
	}

	public RitvikMDBDao getRitvikMDBDao() {
		return ritvikMDBDao;
	}

	public void setRitvikMDBDao(RitvikMDBDao ritvikMDBDao) {
		this.ritvikMDBDao = ritvikMDBDao;
	}


	public ResultObject getAllRitvik() {
		logger.info("Inside service impl of getAllRitvik");
		ResultObject result = ritvikMDBDao.getAllRitVik();
		return result;
	}

	

}
