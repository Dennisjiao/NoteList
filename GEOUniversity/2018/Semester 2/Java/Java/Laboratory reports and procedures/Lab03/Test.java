package student;

public class Test {
public static void main(String[] args) {
student student1=new student("001","李华");
student1.setEnglish(80);
student1.setMaths(80);
student1.setJava(60);
System.out.println(student1+"测试成绩是"+student1.testScore());
student student2=new studentxw("002","小明");
student2.setEnglish(80);
student2.setMaths(90);
student2.setJava(70);
((studentxw) student2).setDuty("监督学习");
System.out.println(student2+"测试成绩是"+student2.testScore()+" 学委的职位是： "+((studentxw) student2).getDuty());

student student3=new studentbz("003","小红");
student3.setEnglish(90);
student3.setMaths(80);
student3.setJava(80);
((studentbz) student3).setDuty("管理班级");
System.out.println(student3+"测试成绩是"+student3.testScore()+" 班长的职位是： "+((studentbz) student3).getDuty());
}
}