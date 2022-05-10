package service;
import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceMockTest {
    @InjectMocks
    public static Service service;
    @Mock
    private Validator<Student> studentValidator;
    @Mock
    private Validator<Homework> homeworkValidator;
    @Mock
    private Validator<Grade> gradeValidator;
    @Mock
    private StudentXMLRepository studentXMLRepository;
    @Mock
    private HomeworkXMLRepository homeworkXMLRepository;
    @Mock
    private GradeXMLRepository gradeXMLRepository;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
        studentValidator = mock(StudentValidator.class);
        homeworkValidator = mock(HomeworkValidator.class);
        gradeValidator = mock(GradeValidator.class);

        studentXMLRepository = mock(StudentXMLRepository.class);
        homeworkXMLRepository = mock(HomeworkXMLRepository.class);
        gradeXMLRepository = mock(GradeXMLRepository.class);

        service = new Service(studentXMLRepository, homeworkXMLRepository, gradeXMLRepository);
    }

    @Test
    public void testSaveStudent() {
        Student student = new Student("15", "John", 234);
        when(studentXMLRepository.save(student)).thenReturn(student);
        Assert.assertEquals(0, service.saveStudent("15", "John", 234));
    }

    @Test
    public void testSaveHomework() {
        Homework homework = new Homework("8", "homework", 4, 3);
        homeworkXMLRepository.save(homework);
        Mockito.verify(homeworkXMLRepository).save(homework);
    }

    @Test
    public void testDeleteStudent() {
        Student student = studentXMLRepository.delete(anyString());
        when(student).thenReturn(student);
    }
}
