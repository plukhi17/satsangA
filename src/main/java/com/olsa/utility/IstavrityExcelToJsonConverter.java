package com.olsa.utility;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.wink.json4j.JSONArray;
//import org.json.JSONOrderedObject;
import org.apache.wink.json4j.JSONException;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.apache.wink.json4j.OrderedJSONObject;
import org.codehaus.jackson.map.ObjectMapper;

//import org.json.JSONException;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
import com.olsa.pojo.IstavritySummaryObject;



public class IstavrityExcelToJsonConverter {
	
	private static final Logger logger = Logger.getLogger(IstavrityExcelToJsonConverter.class);
	//private static final String fileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//TestExcelToJson.xlsx";
	//private static final String JsonFileName ="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//TestExcelToJson.json";
	
	//private static final String fileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//sa_family_master_latest_dec_04.xlsx";
	//private static final String JsonFileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//sa_family_master.json";
	
	private static final String fileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//istavrity_deposit_summary_jan_2018.xlsx";
	private static final String JsonFileName="C://SRI SRI THAKUR//SATSANG_AMERICA//SATSANG_AMERICA_ISTAVRITY//istavrity_deposit_summary_jan_2018.json";
	
	
	public static void main(String [] args ) {
		
		String writeSheetName="NORMAL";
		String writeSheetName2="Sheet2";
		int   nSheetNo=0;          //0 to create sheet for nomall    
		boolean update=false;       //false to create new sheet for normal 
		  
        //Read an Excel File and Store in a Vector
		InputStream uploadfile = null;
               
    	ArrayList<IstavritySummaryObject> cellDataList = null;
	    
    	
		try {
			cellDataList = readExcelFile(uploadfile,fileName,nSheetNo);
			logger.info("CellDataList on Main Method Size : "+cellDataList.size());
			//getJsonFromMyFormObject(cellDataList);
			getJsonFormObject(cellDataList);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("Ecveption occure "+e.getMessage());
			
		}
        //Print the data read
        //printCellDataToConsole(cellDataList);
    }
	
	public static ArrayList <IstavritySummaryObject> readExcelFile(InputStream uploadfile, String fileName, int SheetNo) throws Exception {
		
		//IstavritySummaryObject istavrityMasterObj = new IstavritySummaryObject();	
		//ArrayList<FamilyMasterObject> familyDataList = new ArrayList<FamilyMasterObjec
		
		ArrayList<IstavritySummaryObject> istavrityList = new ArrayList<IstavritySummaryObject>();
		
		HashMap addressMap; 
		HashMap familyMap;
		
		//ArrayList<HashMap> addressList = new ArrayList <HashMap>();
		//ArrayList<HashMap> istavrityList = new ArrayList <HashMap>();

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
				logger.info("XSSFWorkbook OBJECT CREATED");
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
			
			IstavritySummaryObject istSummaryObj=null;
			
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
				istSummaryObj = new IstavritySummaryObject();
				
				//Get the row object
				//Iterator<Cell> cellIterator = row.cellIterator();
				int i =starRowNum;
				//while (cellIterator.hasNext()) row.getCell(5, Row.CREATE_NULL_AS_BLANK).getNumericCellValue()
				//{
					//cell = cellIterator.next();
				      
				istSummaryObj.setSL_NO(row.getCell(0, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				logger.info("Coming 1 ");
				istSummaryObj.setSA_FAMILY_CODE(getStringCheckValue(row.getCell(1, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setSA_MEM_CODE(getStringCheckValue(row.getCell(2, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setIND_FAMILY_CODE(getStringCheckValue(row.getCell(3, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setMONTH_YEAR(getStringCheckValue(row.getCell(4, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				logger.info("Coming 2 ");
				istSummaryObj.setSWASTYAYANI_AMT(row.getCell(5, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setISTAVRITY_AMT(row.getCell(6, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setACHARYAVRITY_AMT(row.getCell(7, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setDAKSHINA_AMT(row.getCell(8, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setSANGATHANI_AMT(row.getCell(9, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setPRONAMI_AMT(row.getCell(10, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setSWASTYAYANI_SURPLUS_AMT(row.getCell(11, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());		
				istSummaryObj.setPARIVRITY_AMT(row.getCell(12, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setRITWIKI_AMT(row.getCell(13, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				istSummaryObj.setTOTAL_AMT(row.getCell(14, Row.CREATE_NULL_AS_BLANK).getNumericCellValue());
				logger.info("Coming 3 ");
				istSummaryObj.setCOLLECTED_ON(getDateCheckValue(row.getCell(15, Row.CREATE_NULL_AS_BLANK).getDateCellValue()));
				istSummaryObj.setCOLLECTED_BY(getStringCheckValue(row.getCell(16, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setPAYMENT_METHOD(getStringCheckValue(row.getCell(17, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				logger.info("Coming 4 ");
				//istSummaryObj.setCHEQUE_NO(getDoubleCheckValue(row.getCell(18, Row.CREATE_NULL_AS_BLANK)));
			    istSummaryObj.setCHEQUE_ISSUE_BANK(getStringCheckValue(row.getCell(19, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
			    logger.info("Coming 5 ");
			    Cell cell1 = row.getCell(20,Row.CREATE_NULL_AS_BLANK);
			    
			    logger.info("Coming 6 ");
			  switch (cell1.getCellType()) {
			    case Cell.CELL_TYPE_STRING:
				    logger.info("String  ");
			    	logger.info(cell1.getRichStringCellValue().getString());
			    	break;

			    case Cell.CELL_TYPE_NUMERIC:
			    	logger.info("Numeric  ");
			    	if (DateUtil.isCellDateFormatted(cell1)) {
			    		logger.info(cell1.getDateCellValue());
			    	} else {
			    		logger.info(cell1.getNumericCellValue());
			    	}
			    	break;

			    case Cell.CELL_TYPE_BOOLEAN:
			    	logger.info("Boolean ");
			    	logger.info(cell1.getBooleanCellValue());
			    	break;

			    case Cell.CELL_TYPE_FORMULA:
			    	logger.info("Formula  ");
			    	logger.info(cell1.getCellFormula());
			    	break;

			    default:
			    	logger.info(" default ");
			    }
			    
			    SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yy");
			    //Date d = sdf.parse(cellValueAsString);
				logger.info("Coming 6 "+row.getCell(20, Row.CREATE_NULL_AS_BLANK).getStringCellValue());    
				
			    istSummaryObj.setCHEQUE_DATE(getDateCheckValue(row.getCell(20, Row.CREATE_NULL_AS_BLANK).getDateCellValue()));
				logger.info("Coming 7 ");
				istSummaryObj.setARGHYA_PRASWASTI_ISSUE(getStringCheckValue(row.getCell(21, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setARGHYA_PRASWASTI_NO(getStringCheckValue(row.getCell(22, Row.CREATE_NULL_AS_BLANK).getStringCellValue()));
				istSummaryObj.setARGHYA_PRASWASTI_ISSUE_DATE(getDateCheckValue(row.getCell(23, Row.CREATE_NULL_AS_BLANK).getDateCellValue()));
				logger.info("Coming 8 ");
				    
				//logger.info("familyObj after reading : "+familyObj.toString());
				
			   //String jsnStr = mapperObj.writeValueAsString(familyObj);
				//logger.info("Converted JSON : "+jsnStr);
				
				istavrityList.add(istSummaryObj);
				logger.info("Number of Rows Read : "+Rctr);
				Rctr++;	    
			} //end of row

		} catch (Exception e) {
			//e.printStackTrace();
			logger.info(e);
			throw new Exception("UPLOAD_EXCEPTION :"+e);
		}
		
		logger.info("Number of Rows extracted from excel: "+istavrityList.size());

		return istavrityList;
	}
	
	public static String getStringCheckValue(String value) {
		
		 if( (value== null) || (value != null && value.length()== 0) ) 
		   return "";
		 else 
		return value;
	}
	
	public static Date getDateCheckValue(Date value) {
		 Date date=null;
		 
		 if( (value== null) ) 
		   return date;
		 else 
		return value;
	}
	
	public static double getDoubleCheckValue(Double value) {
		  double val=0.00;
		 if( (value== null) || (value != null && value== 0) ) 
		   return val;
		 else 
		return value;
	}
	
	public static int getNumberCheckValue(Integer value) {
		 int val=0;
     if((value== null) || (value != null && value== 0) ) 
		   return val;
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

		logger.info("familyList is : "+familyList.size()); 
		
		for (int i = 0; i < familyList.size(); i++) //enter code here`
		{
			//LinkedHashMap<String, String> jsonOrderedMap = new LinkedHashMap<String, String>();
			//jsonOrderedMap = new JSONObject();
			logger.info("For Row "+ i +" Object is : "+familyList.get(i)); 
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
			//logger.info("jsonArray Size is : "+jsonArray.length());
			logger.info("jsonOrderedMap from Json convert : "+jsonOrderedMap);
		}
		//logger.info("jsonOrderedMap Size is : "+jsonOrderedMap.);
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
	
	public static String getJsonFormObject(List<IstavritySummaryObject> istavrityList) 
			throws JSONException, IOException {

		JSONArray addressArray = new JSONArray();
		JSONArray familyArray =null;   //new JSONArray();
		JSONArray jsonArray = new JSONArray();
		OrderedJSONObject jsonOrderedMap=null;

		OrderedJSONObject familymap=null;
		OrderedJSONObject istavrityMap =null;  // new JSONObject();

		String familyCode="";

		logger.info("familyList is : "+istavrityList.size()); 
		
		for (int i = 0; i < istavrityList.size(); i++) //enter code here`
		{
			//LinkedHashMap<String, String> jsonOrderedMap = new LinkedHashMap<String, String>();
			//jsonOrderedMap = new JSONObject();
			logger.info("For Row "+ i +" Object is : "+istavrityList.get(i)); 
			
			if( familyCode.equalsIgnoreCase(istavrityList.get(i).getSA_FAMILY_CODE())) {
				
				familymap = new OrderedJSONObject();
				familymap.put("SWASTYAYANI_AMT",getDoubleCheckValue(istavrityList.get(i).getSWASTYAYANI_AMT()));
				familymap.put("ISTAVRITY_AMT", getDoubleCheckValue(istavrityList.get(i).getISTAVRITY_AMT() ));
				familymap.put("ACHARYAVRITY_AMT",getDoubleCheckValue(istavrityList.get(i).getACHARYAVRITY_AMT()));
				familymap.put("DAKSHINA_AMT",getDoubleCheckValue( istavrityList.get(i).getDAKSHINA_AMT() ));
				familymap.put("SANGATHANI_AMT", getDoubleCheckValue(istavrityList.get(i).getSANGATHANI_AMT() ));
				familymap.put("PRONAMI_AMT", getDoubleCheckValue(istavrityList.get(i).getPRONAMI_AMT()));
				familymap.put("SWASTYAYANI_SURPLUS_AMT",getDoubleCheckValue(istavrityList.get(i).getSWASTYAYANI_SURPLUS_AMT()));
				familymap.put("PARIVRITY_AMT",getDoubleCheckValue(istavrityList.get(i).getPARIVRITY_AMT() ));
				familymap.put("RITWIKI_AMT",getDoubleCheckValue(istavrityList.get(i).getRITWIKI_AMT()));
				familymap.put("TOTAL_AMT", getDoubleCheckValue(istavrityList.get(i).getTOTAL_AMT()));
				familyArray.put(familymap);	
				
			}
			else {
				 
				if (i>0) {
					 jsonOrderedMap.put("line",familyArray);
					 jsonArray.put(jsonOrderedMap);
				 }
				
				familymap = new OrderedJSONObject();
				familyArray =  new JSONArray();
				
				jsonOrderedMap = new OrderedJSONObject();
			
				//istavrityMap = new OrderedJSONObject();
				
				jsonOrderedMap.put("SL_NO",getDoubleCheckValue( istavrityList.get(i).getSL_NO()));
				jsonOrderedMap.put("SA_FAMILY_CODE", getStringCheckValue(istavrityList.get(i).getSA_FAMILY_CODE() ));
				jsonOrderedMap.put("SA_MEM_CODE",getStringCheckValue( istavrityList.get(i).getSA_MEM_CODE() ));
				jsonOrderedMap.put("IND_FAMILY_CODE", getStringCheckValue(istavrityList.get(i).getIND_FAMILY_CODE()));
				jsonOrderedMap.put("MONTH_YEAR",getStringCheckValue(istavrityList.get(i).getMONTH_YEAR()));
				
				//jsonOrderedMap.put("COLLECTED_ON",getStringCheckValue(istavrityList.get(i).getCHEQUE_NO()));
				jsonOrderedMap.put("COLLECTED_BY",getStringCheckValue(istavrityList.get(i).getCOLLECTED_BY() ));
				jsonOrderedMap.put("PAYMENT_METHOD", getStringCheckValue(istavrityList.get(i).getPAYMENT_METHOD() ));
				//jsonOrderedMap.put("CHEQUE_NO",getStringCheckValue(istavrityList.get(i).getCHEQUE_NO() ));
				jsonOrderedMap.put("CHEQUE_ISSUE_BANK",getStringCheckValue( istavrityList.get(i).getCHEQUE_ISSUE_BANK() ));	
				//jsonOrderedMap.put("CHEQUE_DATE",getStringCheckValue(istavrityList.get(i).getCHEQUE_DATE() ));
				jsonOrderedMap.put("ARGHYA_PRASWASTI_ISSUE", getStringCheckValue(istavrityList.get(i).getARGHYA_PRASWASTI_ISSUE() ));
				jsonOrderedMap.put("ARGHYA_PRASWASTI_NO",getStringCheckValue(istavrityList.get(i).getARGHYA_PRASWASTI_NO() ));
				//jsonOrderedMap.put("ARGHYA_PRASWASTI_ISSUE_DATE",getStringCheckValue( istavrityList.get(i).getARGHYA_PRASWASTI_ISSUE_DATE()));
					
			}	
				//jsonOrderedMap.put("family",familyArray);
				//familyArray =  new JSONArray();
				//jsonArray.put(jsonOrderedMap);
		 	familyCode = getStringCheckValue( istavrityList.get(i).getSA_FAMILY_CODE());
		   
			// JSONObject json = new JsonObject(jsonOrderedMap);
			//logger.info("jsonArray Size is : "+jsonArray.length());
			logger.info("jsonOrderedMap from Json convert : "+jsonOrderedMap);
		}
		//logger.info("jsonOrderedMap Size is : "+jsonOrderedMap.);
		//jsonOrderedMap
		//responseDetailsJson.put("obj", jsonArray);
		 jsonOrderedMap.put("line",familyArray);
		 jsonArray.put(jsonOrderedMap);
		FileWriter fileWriter = new FileWriter(JsonFileName);
		// Writting the jsonObject into sample.json
		fileWriter.write(jsonArray.toString(4));
		fileWriter.close();

		return jsonOrderedMap.toString();
	}
	
	
}



