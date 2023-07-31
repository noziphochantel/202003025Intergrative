/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/**
 *
 * @author mlondin
 */



public class ITStudent {

   private String name;
    private int studentId;
    private String programme;
    private int[] courses;
    private int[] marks;
    private double average;
    private boolean passed;

    public ITStudent(String name, int studentId, String programme, int[] courses, int[] marks) {
        this.name = name;
        this.studentId = studentId;
        this.programme = programme;
        this.courses = courses;
        this.marks = marks;
        this.average = calculateAverage();
        this.passed = isPassed();
    }

    private double calculateAverage() {
        int sum = 0;
        for (int mark : marks) {
            sum += mark;
        }

        return sum / marks.length;
    }

    private boolean isPassed() {
        return average >= 50;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
     this.name = name;
    }


    public int getStudentId() {
        return studentId;
    }
    
    //public void setStudentId(String studentId) {
    //this.studentId = studentId;
    //}
    
    public String getProgramme() {
        return programme;
    }
    
    public void setProgramme(String programme) {
    this.programme = programme;
    }
    
    public int[] getCourses() {
        return courses ;
    }
    
    public String getStatus()
    {
        if(passed)
            return "Pass";
        else
            return "Fail";
    }
    
    //public void setCourses(List<Integer> courses) {
    //this.courses = courses;
  //}


    public int[] getMarks() {
        return marks;
    }
    
    //public void setMarks(List<Integer> marks) {
    //this.marks = marks;
  //}

    public double getAverage() {
        return average;
    }
    
    public void setAverage(double average) {
    this.average = average;
    }


    //public boolean isPassed() {
     //   return passed;
    //} 
    
    public void setPassed(boolean passed) {
    this.passed = passed;
    }

    @Override
    public String toString() {
        return "ITStudent{" +
                "name='" + name + '\'' +
                ", studentId=" + studentId +
                ", programme='" + programme + '\'' +
                ", courses=" + Arrays.toString(courses) +
                ", marks=" + Arrays.toString(marks) +
                ", average=" + average +
                ", passed=" + getStatus() +
                '}';
    }

    
    public static ITStudent fromXML(String xml) {
        String[] parts = xml.split("");

        String name = getTagValue(xml,"<name>(.*?)</name>");
        
        int studentId = Integer.parseInt(getTagValue(xml,"<studentId>(.*?)</studentId>"));
        String programme = getTagValue(xml,"<programme>(.*?)</programme>");
        String crs = getTagValue(xml,"<courses>(.*?)</courses>");
        String mrk = getTagValue(xml,"<marks>(.*?)</marks>");
        
        String[] c = crs.replace("[", "")
                .replace("]", "")
                .split(",");
        int[] courses = new int[c.length];
        for(int x = 0; x < courses.length; x++)
        {
            courses[x] = Integer.parseInt(c[x].trim());
        }
        
        String[] m = mrk.replace("[", "")
                .replace("]", "")
                .split(",");
        int[] marks = new int[m.length];
        for(int x = 0; x < courses.length; x++)
        {
            marks[x] = Integer.parseInt(m[x].trim());
        }

        return new ITStudent(name, studentId, programme, courses, marks);
    }
    
    private static String getTagValue(String strXML, String patt)
    {
        Pattern pattern = Pattern.compile(patt);// Pattern.compile("<price>(.*)</price>");
        Matcher matcher = pattern.matcher(strXML);
        
        if( matcher.find() ) 
            return matcher.group(1) ;
        
        return "";
    }
    
   /* public ITStudent fromXML(String xml) {
        //ITStudent student = new ITStudent();

        // Parse the XML into a DOM object.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(xml.getBytes()));

        // Get the root element of the XML document.
        Element rootElement = document.getDocumentElement();

        // Get the name of the student.
        String name = rootElement.getElementsByTagName("name").item(0).getTextContent();
        this.setName(name);

        // Get the student ID.
        String studentId = rootElement.getElementsByTagName("studentId").item(0).getTextContent();
        student.setStudentId(studentId);

        // Get the programme of study.
        String programme = rootElement.getElementsByTagName("programme").item(0).getTextContent();
        student.setProgramme(programme);

        // Get the courses.
        NodeList coursesNodeList = rootElement.getElementsByTagName("courses");
        List<Integer> courses = new ArrayList<>();
        for (int i = 0; i < coursesNodeList.getLength(); i++) {
          Element courseElement = (Element) coursesNodeList.item(i);
          String course = courseElement.getTextContent();
          courses.add(Integer.parseInt(course));
        }
        student.setCourses(courses);

        // Get the marks.
        NodeList marksNodeList = rootElement.getElementsByTagName("marks");
        List<Integer> marks = new ArrayList<>();
        for (int i = 0; i < marksNodeList.getLength(); i++) {
          Element markElement = (Element) marksNodeList.item(i);
          String mark = markElement.getTextContent();
          marks.add(Integer.parseInt(mark));
        }
        student.setMarks(marks);

        // Get the average mark.
        String average = rootElement.getElementsByTagName("average").item(0).getTextContent();
        student.setAverage(Double.parseDouble(average));

        // Get the passed status.
        String passed = rootElement.getElementsByTagName("passed").item(0).getTextContent();
        student.setPassed(Boolean.parseBoolean(passed));

        return student;
      }*/

   public String toXML() {
        return "<student>" +
                "<name>" + name + "</name>" +
                "<studentId>" + studentId + "</studentId>" +
                "<programme>" + programme + "</programme>" +
                "<courses>" + Arrays.toString(courses) + "</courses>" +
                "<marks>" + Arrays.toString(marks) + "</marks>" +
                "<average>" + average + "</average>" +
                "<passed>" + getStatus() + "</passed>" +
                "</student>";
    }
}