package com.qa.junit.studentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.qa.cucumber.serenity.StudentSerenitySteps;
import com.qa.model.StudentClass;
import com.qa.testbase.TestBase;
import com.qa.utils.ReuseableSpecifications;
import com.qa.utils.TestUtils;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class studentCRUDTest extends TestBase{
	
	static String firstName = "User" + TestUtils.getRandomValue();
	static String lastName = "User"+ TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = "test"+ TestUtils.getRandomValue()+"@gmail.com"; 
	static int studentId;
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This is test for create a new student")
	@Test
	public void test001(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, programme, email, courses)
		.statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponseSpec());
	}
	
	@Title("Display the new user with firstname")
	@Test
	public void test002(){
		
		HashMap<String, Object> value = steps.displayStudentWithFistName(firstName);
		
		System.out.println("The value is: " +value);
		
		assertThat(value,hasValue(firstName));
		
		studentId = (int)value.get("id");
	}
	
	@Title("Update firstname for a student with the StudentID")
	@Test
	public void test003(){
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		firstName = firstName + "Updated";
		
		steps.updateStudent(studentId, firstName, lastName, programme, email, courses);
		
		HashMap<String, Object> value = steps.displayStudentWithFistName(firstName);
		
		System.out.println("The value is: " +value);
		
		assertThat(value,hasValue(firstName));
	}
	
	@Title("Delete a student with the studentId and verify the student are not deleted or not!")
	@Test
	public void test004(){
		
		steps.deletedStudent(studentId);
		
		steps.displayStudentWithStudentId(studentId)
		.statusCode(404);
	}
	
	
}
