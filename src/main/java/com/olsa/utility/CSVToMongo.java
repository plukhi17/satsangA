package com.olsa.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olsa.pojo.AddressMDB;
import com.olsa.pojo.FamilyMDB;
import com.olsa.pojo.RootMDB;

public class CSVToMongo {

	public static void main(String[] args) throws IOException {
		List<RootMDB> books = readRootFromCSV("R://root.csv"); // let's print all the person read from CSV file for (Book b : books) { System.out.println(b); }

	
	}
	
	private static List<RootMDB> readRootFromCSV(String fileName) {
		List<RootMDB> roots = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {
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
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString);
		   
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
			root.setRitwikStatus(arg[6]=="LATE"?false:true);
			root.setRitvikName(arg[7] +" " +arg[8] +" " +arg[9] );
			AddressMDB add=new AddressMDB();
			add.setAddressLine1(arg[10]);
			add.setAddressLine2(arg[11]);
			add.setAddressLine3(arg[12]);
			add.setCity(arg[14]);
			add.setState(arg[15]);
		
			add.setCountry(arg[17]);
			add.setZipCode(Long.valueOf( arg[18]));
			
			
			root.setAddress(add);
			root.setEmail(arg[20]);
			root.setPhoneNo(arg[21].replaceAll("\\s+", ""));
			root.setPseronalize(arg[22]);
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
					   List<FamilyMDB> fmllist= new ArrayList();
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
