package com.qa.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.qa.model.StudentClass;
import com.qa.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("Creating student with firstName:{0}, lastName:{1}, programme:{2}, email:{3}, Courses:{4}")
	public ValidatableResponse createStudent(String firstName, String lastName, String programme, String email, List<String> courses){
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then()
		.log()
		.all();
	}
	
	@Step("Getting student information with firstname:{0}")
	public HashMap<String, Object> displayStudentWithFistName(String firstName){
		String p1="findAll{it.firstName=='";
		String p2="'}.get(0)";
		
		return SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200)
		.extract()
		.path(p1+firstName+p2);
	}
	
	@Step("Updating student with studentId:{0}, firstName:{1}, lastName:{2}, programme:{3}, email:{4}, Courses:{5}")
	public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String programme, String email, List<String> courses){
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.put("/"+studentId)
		.then();
	}
	
	@Step("Deleting student with studentId:{0}")
	public void deletedStudent(int studentId){
		SerenityRest.rest()
		.given()
		.when()
		.delete("/"+studentId);
	}
	
	@Step("Getting student information with studentId:{0}")
	public ValidatableResponse displayStudentWithStudentId(int studentId){
		return SerenityRest.rest().given()
		.when()
		.get("/"+ studentId)
		.then()
		.log()
		.all();
	}
}
