package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;


public class ServiceTest {
    public static Service service;

    @org.junit.jupiter.api.BeforeAll
    static void init() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.Test
    public void testSaveStudentShouldPass() {
        // ha 0 akkor sikerult a beszuras
        Assert.assertEquals(0, service.saveStudent("5", "John Smith", 227));
    }

    @org.junit.jupiter.api.Test
    public void testSaveHomeworkShouldPass() {
        Assert.assertNotEquals(1, service.saveHomework("7", "new homework", 4, 3));
    }

    @org.junit.jupiter.api.Test
    public void testSaveGradeShouldPass() {
        int val = service.saveGrade("5", "2", 10.0, 3, "great job");
        Assert.assertEquals(1, val);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4"})
    public void testDeleteStudent(String input) {
        int val = service.deleteStudent(input);
        Assert.assertEquals(1, val);
    }

    @org.junit.jupiter.api.Test
    public void testDeleteHomework() {
        Assert.assertTrue(service.deleteHomework("1"));
    }
}