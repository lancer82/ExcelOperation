package compare;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import modules.Student2;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: lp_jo
 * @date: 9/7/2021
 * @description:
 */
public class Students2Compare {

    private static List<Student2> stu2List;
    private static List<Student2> stu2sList;
    static String  tempPath = "/student2_map_template.xlsx";
    private static final List<Student2> class2 = new ArrayList<>();
    private static final List<Student2> class4 = new ArrayList<>();

    @BeforeClass
    public static void ReadCsvForTheTest() {
        String pathSrc = "src/test/resources/students_02.csv";
        String pathTar = "src/test/resources/students_02s.csv";
        stu2List = ExcelUtils.getInstance().readCSV2Objects(pathSrc,Student2.class);
        stu2sList = ExcelUtils.getInstance().readCSV2Objects(pathTar,Student2.class);
    }

    @Test
    public void compareEach() {
       assertThat(stu2List.toString()).isEqualTo(stu2sList.toString());
    }

    @Test
    public void compareSpecific() throws Exception{
        ListIterator<Student2> it = stu2List.listIterator();
        List<Student2> student2List;

        while(it.hasNext()){
            Student2 stu = it.next();
            Long id = stu.getId();
            student2List = stu2sList.stream().filter(stu2 -> stu2.getId().equals(id)).collect(Collectors.toList());
            if (student2List.size() == 1) {
                class2.add(new Student2(stu.getId(),stu.getName(),stu.getDate(),stu.getClasses(),stu.isExpel()));
            } else {
                class4.add(new Student2(stu.getId(),stu.getName(),stu.getDate(),stu.getClasses(),stu.isExpel()));
            }
        }
    }

    @AfterClass
    public static void tearDown() throws Excel4JException {
        Map<String, List<?>> classes = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        data.put("title", "?????????????????????");
        data.put("info", "?????????????????????");
        classes.put("class_two", Arrays.asList(class2.toArray()));
        classes.put("class_four",Arrays.asList(class4.toArray()));
        ExcelUtils.getInstance().exportMap2Excel(tempPath,
                0, classes, data, Student2.class, false, "report.xlsx");
    }
}
