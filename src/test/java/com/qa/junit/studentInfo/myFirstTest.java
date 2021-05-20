package com.qa.junit.studentInfo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class myFirstTest{
	
	@BeforeClass
	public static void init(){
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	
	@Test
	public void getAllStudent(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	@Test
	public void thisIsaFailing(){
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void thisIsaPending(){
		
	}
	
	@Ignore
	@Test
	public void thisIsASkip(){
		
	}
	
	@Test
	public void thisIsaError(){
		System.out.println("The errors is occured on " + (5/0));
	}
	
	@Test
	public void fileIsNotExit() throws FileNotFoundException{
		File file = new File("D:\\text.txt");
		FileReader fr = new FileReader(file);
	}
	
	@Manual
	@Test
	public void thisIsAManualTest(){
		
	}
}
