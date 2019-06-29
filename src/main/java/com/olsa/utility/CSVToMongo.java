package com.olsa.utility;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olsa.pojo.AddressMDB;
import com.olsa.pojo.FamilyMDB;
import com.olsa.pojo.IshtLineMDB;
import com.olsa.pojo.IshtMDB;
import com.olsa.pojo.RootMDB;

public class CSVToMongo {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		//List<RootMDB> books = readRootFromCSV("P://ghana//parth12.csv"); // let's print all the person read from CSV file for (Book b : books) { System.out.println(b); }
		List<IshtMDB> ishts = readIshtFromCSV("P://ghana//ist1.csv");
		
	}
	
	private static List<IshtMDB> readIshtFromCSV(String fileName) {

		List<IshtMDB> ishts = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
			// read the first line from the text file
				String line = br.readLine(); 
				int linecount=0;
			// loop until all lines are read 
				while (line != null) {
					// use string.split to load a string array with the values from
					// each line of 
					// the file, using a comma as the delimiter
		
					IshtMDB root = null;
					if(linecount!=0){
						String[] attributes = line.split(","); 
						 try {
							 if(attributes.length!=0) {
								 root = createIsht(attributes,ishts);
							 }else {
								 break;
							 }
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						 if(root!=null){
							 ishts.add(root); 
						 }
							 
					}
					
					
				
					// read next line before looping 
					// if end of file reached, line would be null 
					line = br.readLine(); 
					linecount++;
					}
				}
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		DateFormat df = new SimpleDateFormat(OnlineSAConstants.DATE_TIME_FORMAT_MONGO);
		mapper.setDateFormat(df);
		String jsonString="";
		try {
			//jsonString = mapper.writeValueAsString(ishts);
			String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ishts);
			try (FileWriter file = new FileWriter("P://ghana//Resist11.json")) {
				file.write(indented);
				System.out.println("Successfully Copied JSON Object to File...");
				}
				

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException io) {
			io.printStackTrace();
		}
		
		return ishts;
	
	}

	private static IshtMDB createIsht(final String[] arg, List<IshtMDB> ishts) throws ParseException {
		Double total=0.0;
		IshtMDB isht=null;
	
		
			if (! ishts.stream().anyMatch(new Predicate<IshtMDB>() {
				@Override
				public boolean test(IshtMDB t) {
					return t.getReceiptNo().equals(arg[23]);
				}}) ) {
				isht=new IshtMDB();
				isht.setFamilyID(arg[1]);
				isht.setMonthYear(arg[4]);
				isht.setCollectedBy(arg[17].toUpperCase());
				isht.setCollectedOn(arg[16]);
				
				isht.setChecqNo(arg[19]);
				isht.setChequeIssueBank(arg[20]);
				try {
					if(arg[21]!=null ) {
						isht.setChecqDate(DateUtility.formateDate2(arg[21]));
					}
					
				}catch(Exception e) {
					
					System.out.println("Exception "+arg[0]);
					e.printStackTrace();
					
				}
				
				isht.setIssuedFlag((arg[22]!=null && arg[22].equals("YES"))?"Y":"N");
				isht.setReceiptNo(arg[23]);
				isht.setReceiptDate(arg[24]);
				isht.setActive("Y");
				if(arg[18].equals("E TRANSFER")) {
					isht.setApprovedBy("SYSTEM");
					isht.setApprovedOn(arg[24]);
					isht.setPaymentMethod("AUTO");
					isht.setSubmittedOn(isht.getChecqDate());
					
					
				}else {
					//isht.setApprovedBy("SYSTEM");
					isht.setApprovedOn(arg[24]);
					isht.setPaymentMethod("MANUAL");
				}
				
				List<IshtLineMDB> lineLst= new ArrayList<IshtLineMDB>();
				IshtLineMDB line=null;
				try {
					if(arg[2].split("-")[1].equals("01")){
						
					}
				}
				catch(Exception e) {
					System.out.println("----------------"+arg[1]);
					System.out.println("whatsthe mater");
				}
				if(arg[2].split("-")[1].equals("01")) {
					line= new IshtLineMDB();
					line.setId(arg[2]);
					line.setSwastyayani(Double.valueOf( arg[5]));
					line.setIstavrity(Double.valueOf( arg[6]));
					line.setAcharyavrity(Double.valueOf( arg[7]));
					line.setDakshina(Double.valueOf( arg[8]));
					line.setSangathani(Double.valueOf( arg[9]));
					line.setPronami(Double.valueOf( arg[10]));
					line.setSurplus(Double.valueOf( arg[12]));
					line.setParivrity(Double.valueOf( arg[13]));
					line.setRitwiki(Double.valueOf( arg[14]));
					line.setTotal(Double.valueOf( arg[15]));
					total+=Double.valueOf( arg[15]);
					lineLst.add(line);
				}
				isht.setLine(lineLst);
				isht.setTotal(total);
			}else {
				Iterator<IshtMDB> ishtItr= ishts.iterator();
				IshtLineMDB line=null;
			   while(ishtItr.hasNext()){
				   
				   IshtMDB parent= ishtItr.next();
				   if(parent.getReceiptNo().equals(arg[23]) && !arg[2].split("-")[1].equals("01") ){
					   	line= new IshtLineMDB();
						line.setId(arg[2]);
						line.setSwastyayani(Double.valueOf( arg[5]));
						line.setIstavrity(Double.valueOf( arg[6]));
						line.setAcharyavrity(Double.valueOf( arg[7]));
						line.setDakshina(Double.valueOf( arg[8]));
						line.setSangathani(Double.valueOf( arg[9]));
						line.setPronami(Double.valueOf( arg[10]));
						line.setSurplus(Double.valueOf( arg[12]));
						line.setParivrity(Double.valueOf( arg[13]));
						line.setRitwiki(Double.valueOf( arg[14]));
						line.setTotal(Double.valueOf( arg[15]));
						parent.setTotal(parent.getTotal()+Double.valueOf( arg[15]));
						parent.getLine().add(line);
				   }
			   }
			}
			return isht;
	}
		

	private static List<RootMDB> readRootFromCSV(String fileName) {
		List<RootMDB> roots = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile,  Charset.defaultCharset())) {
			// read the first line from the text file
				String line = br.readLine(); 
				int linecount=0;
			// loop until all lines are read 
				while (line != null) {
					// use string.split to load a string array with the values from
					// each line of 
					// the file, using a comma as the delimiter
		
					RootMDB root = null;
					if(linecount!=0){
						String[] attributes = line.split(","); 
						 root = createRoot(attributes,roots); 
						 if(root!=null){
							 roots.add(root); 
						 }
							 
					}
					
					
				
					// read next line before looping 
					// if end of file reached, line would be null 
					line = br.readLine(); 
					linecount++;
					}
				}
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		String jsonString="";
		try {
			jsonString = mapper.writeValueAsString(roots);
			String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(roots);
			try (FileWriter file = new FileWriter("P://ghana//Resroot11.json")) {
				file.write(indented);
				System.out.println("Successfully Copied JSON Object to File...");
				}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
		
		   
		return roots;
	}

	private static RootMDB createRoot(final String[] arg,List<RootMDB> roots) {
		RootMDB root= null;
		//System.out.println(arg[0] + "----"+roots.stream().anyMatch(ti -> ti.getFamilyID().equals(arg[0])));
		System.out.println();
	
	
		if (! roots.stream().anyMatch(new Predicate<RootMDB>() {
			@Override
			public boolean test(RootMDB ti) {
				return ti.getFamilyID().equals(arg[0]);
			}
		}) ) {
			root=new RootMDB();
			root.setFamilyID(arg[0]);
			root.setPersonalId(arg[1]);
			root.setIndfamilyCode(arg[2]);
			root.setFirstName(arg[3]);
			root.setMiddleName(arg[4]);
			root.setLastName(arg[5]);
			if(arg[6]!=null) {
				root.setRitwikStatus(arg[6]=="LATE"?false:true);
			}
			
			root.setrName(arg[7] +" " +arg[8] +" " +arg[9] );
			AddressMDB add=new AddressMDB();
			add.setAddressLine1(arg[10]);
			add.setAddressLine2(arg[11]);
			add.setAddressLine3(arg[12]);
			add.setCity(arg[14]);
			add.setState(arg[15]);
		
			add.setCountry(arg[17]);
			
			
			try {
				if(!arg[18].equals("") && arg[18]!=null) {
					add.setZipCode(Long.valueOf(arg[18]));
				}
				
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(arg[1]);
			}
			
			root.setAddress(add);
			try {
			
				root.setEmail(arg[20]!= null?arg[20]:"");
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(arg[1]);
			}
			
			try {
				root.setPhoneNo(arg[21]!=null?arg[21].replaceAll("\\s+", ""):"");
			}catch(Exception e) {
				System.out.println(arg[1]);
			}
			
			//root.setPseronalize(arg[22]);
			try {
				root.setPseronalize(arg[22]!=null?arg[22]:"");
			}catch(Exception e) {
				System.out.println(arg[1]);
			}
			root.setPassword("Pz9cQmdxSj8/Vj8/Pz88KQ==,-1246001394");
			root.setMigrated(true);
			root.setFirstLogin(true);
		} else {
			Iterator<RootMDB> rootItr= roots.iterator();
		   while(rootItr.hasNext()){
			   
			   RootMDB parent= rootItr.next();
			   if(parent.getFamilyID().equals(arg[0])){
				   FamilyMDB fml= new FamilyMDB();
				   fml.setPersonalID(arg[1]);
				   fml.setFirstName(arg[3]);
				   fml.setMiddleName(arg[4]);
				   fml.setLastName(arg[5]);
				   fml.setRitwikStatus(arg[6]=="LATE"?false:true);
				   fml.setrName(arg[7] +" " +arg[8] +" " +arg[9] );
				   if(parent.getFamily()==null){
					   List<FamilyMDB> fmllist= new ArrayList<FamilyMDB>();
					   fmllist.add(fml);
					   parent.setFamily(fmllist);
					  
				   }else{
					   parent.getFamily().add(fml);
				   }
				   break;
			   }
			
		   }
		}
			
			
		return root;
	}

	
}
