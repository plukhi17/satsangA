package com.olsa.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.apache.wink.json4j.OrderedJSONObject;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONArray;
//import org.json.JSONOrderedObject;


	
//import org.json.JSONException;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
import com.olsa.pojo.FamilyMasterObject;

//import com.cisco.icit.usap.data.ContractAuditData;
//import com.cisco.icit.usap.data.ContractExceptionBean;
//import com.cisco.icit.usap.data.ReorderProductData;
//import com.cisco.icit.usap.data.UploadMappingData;


public class SaFamilyMasterExcelToJsonConverter {
	static final Logger logger = Logger.getLogger(SaFamilyMasterExcelToJsonConverter.class);
		
	//private static final String fileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//TestExcelToJson.xlsx";
	//private static final String JsonFileName ="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//TestExcelToJson.json";
	
	private static final String fileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//sa_family_master_latest_dec_04.xlsx";
	private static final String JsonFileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//sa_family_master.json";
	
	public static void main(String [] args ) {
		
		String writeSheetName="NORMAL";
		String writeSheetName2="Sheet2";
		int   nSheetNo=0;          //0 to create sheet for nomall    
		boolean update=false;       //false to create new sheet for normal 
		  
        //Read an Excel File and Store in a Vector
		InputStream uploadfile = null;
               
    	ArrayList<FamilyMasterObject> cellDataList = null;
    	
		try {
			cellDataList = readExcelFile(uploadfile,fileName,nSheetNo);
			logger.info("cellDataList on Main Method Size : "+cellDataList.size());
			//getJsonFromMyFormObject(cellDataList);
			getJsonFormObject(cellDataList);
		} catch (Exception e) {
			logger.info(e.getMessage());
			//e.printStackTrace();
		}
        //Print the data read
        //printCellDataToConsole(cellDataList);
    }
	
	public static ArrayList<FamilyMasterObject> readExcelFile(InputStream uploadfile, String fileName, int SheetNo) throws Exception {
		//ArrayList<ArrayList<PrayerTime>> prayerTimeList = new ArrayList<ArrayList<String>>();
		ArrayList<FamilyMasterObject> familyDataList = new ArrayList<FamilyMasterObject>();

		FamilyMasterObject familyMasterObj = new FamilyMasterObject();	
		HashMap addressMap; 
		HashMap familyMap;
		
		ArrayList<HashMap> addressList = new ArrayList <HashMap>();
		ArrayList<HashMap> familyist = new ArrayList <HashMap>();

		try {
			// Create a new instance for WorkBook Class
			Workbook workBook = null;   		
			logger.info("Inside Excel Utils");
			if (fileName.endsWith(".xlsx")) {
				logger.info(".xlsx document found in Excel Utils");
				FileInputStream myInput = new FileInputStream(fileName);
				//workBook = new XSSFWorkbook(uploadfile);
				workBook = new XSSFWorkbook(myInput);
				workBook.setMissingCellPolicy(HSSFRow.RETURN_NULL_AND_BLANK);
				logger.info("XSSFWorkbook OBJECT CREATED..................");
			} else {
				logger.info("Other document found in Excel Utils");
				//POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
				workBook = new HSSFWorkbook(new POIFSFileSystem(uploadfile));
			}
			//readWorkbook(workBook, cellDataList);
			logger.info("Start Reading Workbook");
			Sheet hssfSheet = workBook.getSheetAt(SheetNo);
			//Sheet hssfSheet = workBook.getSheetAt(1);
			// Iterate the rows and cells of the spreadsheet to get all the
			Iterator rowIterator = hssfSheet.rowIterator();
			Cell cell = null;
			Row row = null;
			int starRowNum=0;
			int Rctr = 0;
			int Cctr=0;
			int monthCtr=0;
			int headerCounter=0;
			boolean emptyFlag=false;
			
			FamilyMasterObject familyObj=null;
			
			ObjectMapper mapperObj = new ObjectMapper();
			String jsonStr="";
	        String familyCode=""; 
			
			while (rowIterator.hasNext()) {
				row = (Row) rowIterator.next();
				//HSSFCell cell=row.getCell(c, org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK );
				String strValue = null;
				int intValue = 0;
				Cctr=0;
				monthCtr=0;
				familyObj = new FamilyMasterObject();
				
				//Get the row object
				//Iterator<Cell> cellIterator = row.cellIterator();
				int i =starRowNum;
				//while (cellIterator.hasNext()) 
				//{
					//cell = cellIterator.next();
				      
				      familyObj.setSA_FAMILY_CODE(getStringCheckValue(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setSA_MEM_CODE(getStringCheckValue(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setIND_FAMILY_CODE(getStringCheckValue(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setFIRST_NAME(getStringCheckValue(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setMIDDLE_NAME(getStringCheckValue(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setLAST_NAME(getStringCheckValue(row.getCell(5, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setRITWIK_STATUS(getStringCheckValue(row.getCell(6, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setRITWIK_FIRST_NAME(getStringCheckValue(row.getCell(7, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setRITWIK_MIDDLE_NAME(getStringCheckValue(row.getCell(8, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setRITWIK_LAST_NAME(getStringCheckValue(row.getCell(9, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				  
				      familyObj.setADDRESS1(getStringCheckValue(row.getCell(10, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setADDRESS2(getStringCheckValue(row.getCell(11, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setADDRESS3(getStringCheckValue(row.getCell(12, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setCITY(getStringCheckValue(row.getCell(13, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setSTATE(getStringCheckValue(row.getCell(14, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setCOUNTRY(getStringCheckValue(row.getCell(15, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setZIP_CODE(getStringCheckValue(row.getCell(16, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      
				      /*
				      if( familyCode.equalsIgnoreCase(familyObj.getSA_FAMILY_CODE())) {
				    	  
				    	  familyMap = new HashMap(); 
				    	  familyMap.put("personalID",familyObj.getSA_MEM_CODE());
				    	  familyMap.put("firstName",familyObj.getFIRST_NAME());
				    	  familyMap.put("middleName",familyObj.getMIDDLE_NAME());
				    	  familyMap.put("lastName",familyObj.getLAST_NAME());
				    	  familyMap.put("ritwikID","");
				    	  familyMap.put("ritwikStatus","false");
				    	  familyMap.put("rName",familyObj.getRITWIK_FIRST_NAME()+" "+ familyObj.getRITWIK_MIDDLE_NAME()+" "+familyObj.getLAST_NAME());
				    	  //familyMap.put("active","true");
				    	  addressList.add(familyMap);
				      }
				      else {
				    	  familyObj.setADDRESS1(getStringCheckValue(row.getCell(10, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setADDRESS2(getStringCheckValue(row.getCell(11, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setADDRESS3(getStringCheckValue(row.getCell(12, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setCITY(getStringCheckValue(row.getCell(13, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setSTATE(getStringCheckValue(row.getCell(14, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setCOUNTRY(getStringCheckValue(row.getCell(15, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      familyObj.setZIP_CODE(getStringCheckValue(row.getCell(16, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
					      
				    	  addressMap= new HashMap();
				    	  addressMap.put("addressLine1", familyObj.getADDRESS1());
				    	  addressMap.put("addressLine2", familyObj.getADDRESS2());
				    	  addressMap.put("addressLine3", familyObj.getADDRESS3());
				    	  addressMap.put("city",familyObj.getCITY());
				    	  addressMap.put("state",familyObj.getSTATE());
				    	  addressMap.put("country",familyObj.getCOUNTRY());
				    	  addressMap.put("zipCode",familyObj.getZIP_CODE());
				      }
				      */
				      
				      familyObj.setARGHYA_PRASWASTI_PAPERLESS_SETUP(getStringCheckValue(row.getCell(17, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setEMAIL_ID(getStringCheckValue(row.getCell(18, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setPRIMARY_PHONE(getStringCheckValue(row.getCell(19, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				      familyObj.setPERSONALIZE(getStringCheckValue(row.getCell(20, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
			
					
				//System.out.println("familyObj after reading : "+familyObj.toString());
				
			//	String jsnStr = mapperObj.writeValueAsString(familyObj);
				//System.out.println("Converted JSON : "+jsnStr);
				
				familyDataList.add(familyObj);
				//System.out.println("Number of Rows Read : "+Rctr);
				 familyCode=familyObj.getSA_FAMILY_CODE();
				Rctr++;	    
			} //end of row

		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Exception occure while read Excel File method  "+e.getMessage());
			throw new Exception("UPLOAD_EXCEPTION :"+e);
		}
		
		logger.info("Number of Rows extracted from excel: "+familyDataList.size());

		return familyDataList;
	}
	
	public static String getStringCheckValue(String value) {
		
		 if( (value== null) || (value != null && value.length()== 0) ) 
		   return "";
		 else 
		return value;
	}
	
	private Object getCellValue(Cell cell) {
		
	    switch (cell.getCellType()) 
	    {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	      
	    case Cell.CELL_TYPE_BLANK:   
	        return "";
	    }
	 
	    return null;
	}
	
	/*
	public static String getJsonFromMyFormObject(List<FamilyMasterObject> familyList) 
			throws JSONException, IOException {

		JSONArray addressArray = new JSONArray();
		JSONArray familyArray =null;   //new JSONArray();
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonOrderedMap=null;

		JSONObject addressMap = new JSONObject();
		JSONObject familyMap =null;  // new JSONObject();

		String familyCode="";

		System.out.println("familyList is : "+familyList.size()); 
		
		for (int i = 0; i < familyList.size(); i++) //enter code here`
		{
			//LinkedHashMap<String, String> jsonOrderedMap = new LinkedHashMap<String, String>();
			//jsonOrderedMap = new JSONObject();
			System.out.println("For Row "+ i +" Object is : "+familyList.get(i)); 
			if( familyCode.equalsIgnoreCase(familyList.get(i).getSA_FAMILY_CODE())) {
				
				familyMap = new JSONObject();
				familyMap.put("personalId",getStringCheckValue( familyList.get(i).getSA_MEM_CODE()));
				familyMap.put("firstName", getStringCheckValue(familyList.get(i).getFIRST_NAME()));
				familyMap.put("middleName",getStringCheckValue( familyList.get(i).getMIDDLE_NAME()));
				familyMap.put("lastName", getStringCheckValue(familyList.get(i).getLAST_NAME()));
				
				familyMap.put("ritwikStatus",getStringCheckValue(familyList.get(i).getRITWIK_STATUS()));
				familyMap.put("ritwikFirstName", getStringCheckValue(familyList.get(i).getRITWIK_FIRST_NAME()));
				familyMap.put("ritwikMiddleName",getStringCheckValue(familyList.get(i).getRITWIK_MIDDLE_NAME()));
				familyMap.put("ritwikLastName",getStringCheckValue( familyList.get(i).getRITWIK_LAST_NAME()));	
				familyArray.put(familyMap);	
			}
			else {
				 
				if (i>0) {
					 
					 jsonOrderedMap.put("address",addressMap);
					 jsonOrderedMap.put("family",familyArray);
					 jsonArray.put(jsonOrderedMap);
				 }
				
				familyMap = new JSONObject();
				addressMap =  new JSONObject();
				familyArray =  new JSONArray();
				jsonOrderedMap = new JSONObject();
				
				jsonOrderedMap.put("familyID", getStringCheckValue(familyList.get(i).getSA_FAMILY_CODE()));
				jsonOrderedMap.put("personalId", getStringCheckValue(familyList.get(i).getSA_MEM_CODE()));
				jsonOrderedMap.put("IND_FAMILY_CODE",getStringCheckValue(familyList.get(i).getIND_FAMILY_CODE()));

				jsonOrderedMap.put("firstName",getStringCheckValue(familyList.get(i).getFIRST_NAME()));
				jsonOrderedMap.put("middleName",getStringCheckValue(familyList.get(i).getMIDDLE_NAME()));
				jsonOrderedMap.put("lastName", getStringCheckValue(familyList.get(i).getLAST_NAME()));

				jsonOrderedMap.put("ritwikStatus",getStringCheckValue(familyList.get(i).getRITWIK_STATUS()));
				jsonOrderedMap.put("ritwikFirstName", getStringCheckValue(familyList.get(i).getRITWIK_FIRST_NAME()));
				jsonOrderedMap.put("ritwikMiddleName",getStringCheckValue(familyList.get(i).getRITWIK_MIDDLE_NAME()));
				jsonOrderedMap.put("ritwikLastName",getStringCheckValue( familyList.get(i).getRITWIK_LAST_NAME()));	
				
				//building adderss object 
				addressMap.put("addressLine1",getStringCheckValue(familyList.get(i).getADDRESS1()));
				addressMap.put("addressLine2",getStringCheckValue( familyList.get(i).getADDRESS2()));
				addressMap.put("addressLine3",getStringCheckValue( familyList.get(i).getADDRESS3()));
				addressMap.put("city",getStringCheckValue( familyList.get(i).getCITY()));
				addressMap.put("state",getStringCheckValue( familyList.get(i).getSTATE()));
				addressMap.put("country",getStringCheckValue( familyList.get(i).getCOUNTRY()));
				addressMap.put("zipCode",getStringCheckValue( familyList.get(i).getZIP_CODE()));
				//jsonOrderedMap.put("address",addressMap);
				//building adderss object 
				
				jsonOrderedMap.put("ARGHYA_PRASWASTI_PAPERLESS_SETUP",familyList.get(i).getARGHYA_PRASWASTI_PAPERLESS_SETUP() );
				jsonOrderedMap.put("email", familyList.get(i).getEMAIL_ID());
				jsonOrderedMap.put("phoneNo", familyList.get(i).getPRIMARY_PHONE());
				jsonOrderedMap.put("pseronalize", familyList.get(i).getPERSONALIZE());
				jsonOrderedMap.put("password", "Temp123");
				
				//jsonOrderedMap.put("family",familyArray);
				//familyArray =  new JSONArray();
				//jsonArray.put(jsonOrderedMap);
			}
			
			familyCode = getStringCheckValue( familyList.get(i).getSA_FAMILY_CODE());
		   
			// JSONObject json = new JsonObject(jsonOrderedMap);
			//System.out.println("jsonArray Size is : "+jsonArray.length());
			System.out.println("jsonOrderedMap from Json convert : "+jsonOrderedMap);
		}
		//System.out.println("jsonOrderedMap Size is : "+jsonOrderedMap.);
		//jsonOrderedMap
		//responseDetailsJson.put("obj", jsonArray);
		 jsonOrderedMap.put("address",addressMap);
		 jsonOrderedMap.put("family",familyArray);
		 jsonArray.put(jsonOrderedMap);
		FileWriter fileWriter = new FileWriter(JsonFileName);
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString(4));
		fileWriter.close();

		return jsonOrderedMap.toString();
	}
	
	*/
	
	public static String getJsonFormObject(List<FamilyMasterObject> familyList) 
			throws JSONException, IOException {

		JSONArray addressArray = new JSONArray();
		JSONArray familyArray =null;   //new JSONArray();
		JSONArray jsonArray = new JSONArray();
		OrderedJSONObject jsonOrderedMap=null;

		OrderedJSONObject addressMap = new OrderedJSONObject();
		OrderedJSONObject familyMap =null;  // new JSONObject();

		String familyCode="";

		logger.info("familyList is : "+familyList.size()); 
		
		for (int i = 0; i < familyList.size(); i++) //enter code here`
		{
			//LinkedHashMap<String, String> jsonOrderedMap = new LinkedHashMap<String, String>();
			//jsonOrderedMap = new JSONObject();
			logger.info("For Row "+ i +" Object is : "+familyList.get(i)); 
			if( familyCode.equalsIgnoreCase(familyList.get(i).getSA_FAMILY_CODE())) {
				
				familyMap = new OrderedJSONObject();
				familyMap.put("personalId",getStringCheckValue( familyList.get(i).getSA_MEM_CODE()));
				familyMap.put("firstName", getStringCheckValue(familyList.get(i).getFIRST_NAME()));
				familyMap.put("middleName",getStringCheckValue( familyList.get(i).getMIDDLE_NAME()));
				familyMap.put("lastName", getStringCheckValue(familyList.get(i).getLAST_NAME()));
				
				familyMap.put("ritwikId","");
				familyMap.put("ritwikStatus",getStringCheckValue(familyList.get(i).getRITWIK_STATUS()));
				familyMap.put("ritwikFirstName", getStringCheckValue(familyList.get(i).getRITWIK_FIRST_NAME()));
				familyMap.put("ritwikMiddleName",getStringCheckValue(familyList.get(i).getRITWIK_MIDDLE_NAME()));
				familyMap.put("ritwikLastName",getStringCheckValue( familyList.get(i).getRITWIK_LAST_NAME()));	
				familyArray.put(familyMap);	
			}
			else {
				 
				if (i>0) {
					 
					 jsonOrderedMap.put("address",addressMap);
					 jsonOrderedMap.put("family",familyArray);
					 jsonArray.put(jsonOrderedMap);
				 }
				
				familyMap = new OrderedJSONObject();
				addressMap =  new OrderedJSONObject();
				familyArray =  new JSONArray();
				jsonOrderedMap = new OrderedJSONObject();
				
				jsonOrderedMap.put("familyID", getStringCheckValue(familyList.get(i).getSA_FAMILY_CODE()));
				jsonOrderedMap.put("personalId", getStringCheckValue(familyList.get(i).getSA_MEM_CODE()));
				jsonOrderedMap.put("IND_FAMILY_CODE",getStringCheckValue(familyList.get(i).getIND_FAMILY_CODE()));

				jsonOrderedMap.put("firstName",getStringCheckValue(familyList.get(i).getFIRST_NAME()));
				jsonOrderedMap.put("middleName",getStringCheckValue(familyList.get(i).getMIDDLE_NAME()));
				jsonOrderedMap.put("lastName", getStringCheckValue(familyList.get(i).getLAST_NAME()));
				
				jsonOrderedMap.put("ritwikId","");
				jsonOrderedMap.put("ritwikStatus",getStringCheckValue(familyList.get(i).getRITWIK_STATUS()));
				jsonOrderedMap.put("ritwikFirstName", getStringCheckValue(familyList.get(i).getRITWIK_FIRST_NAME()));
				jsonOrderedMap.put("ritwikMiddleName",getStringCheckValue(familyList.get(i).getRITWIK_MIDDLE_NAME()));
				jsonOrderedMap.put("ritwikLastName",getStringCheckValue( familyList.get(i).getRITWIK_LAST_NAME()));	
				
				//building adderss object 
				addressMap.put("addressLine1",getStringCheckValue(familyList.get(i).getADDRESS1()));
				addressMap.put("addressLine2",getStringCheckValue( familyList.get(i).getADDRESS2()));
				addressMap.put("addressLine3",getStringCheckValue( familyList.get(i).getADDRESS3()));
				addressMap.put("city",getStringCheckValue( familyList.get(i).getCITY()));
				addressMap.put("state",getStringCheckValue( familyList.get(i).getSTATE()));
				addressMap.put("country",getStringCheckValue( familyList.get(i).getCOUNTRY()));
				addressMap.put("zipCode",getStringCheckValue( familyList.get(i).getZIP_CODE()));
				//jsonOrderedMap.put("address",addressMap);
				//building adderss object 
				
				jsonOrderedMap.put("ARGHYA_PRASWASTI_PAPERLESS_SETUP",familyList.get(i).getARGHYA_PRASWASTI_PAPERLESS_SETUP() );
				jsonOrderedMap.put("email", familyList.get(i).getEMAIL_ID());
				jsonOrderedMap.put("phoneNo", familyList.get(i).getPRIMARY_PHONE());
				jsonOrderedMap.put("pseronalize", familyList.get(i).getPERSONALIZE());
				jsonOrderedMap.put("password", "Temp123");
				
				//jsonOrderedMap.put("family",familyArray);
				//familyArray =  new JSONArray();
				//jsonArray.put(jsonOrderedMap);
			}
			
			familyCode = getStringCheckValue( familyList.get(i).getSA_FAMILY_CODE());
		   
			// JSONObject json = new JsonObject(jsonOrderedMap);
			//System.out.println("jsonArray Size is : "+jsonArray.length());
			logger.info("jsonOrderedMap from Json convert : "+jsonOrderedMap);
		}
		//System.out.println("jsonOrderedMap Size is : "+jsonOrderedMap.);
		//jsonOrderedMap
		//responseDetailsJson.put("obj", jsonArray);
		 jsonOrderedMap.put("address",addressMap);
		 jsonOrderedMap.put("family",familyArray);
		 jsonArray.put(jsonOrderedMap);
		FileWriter fileWriter = new FileWriter(JsonFileName);
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString(4));
		fileWriter.close();

		return jsonOrderedMap.toString();
	}
	
	
	
}



