package student;

public class Test {
public static void main(String[] args) {
student student1=new student("001","�");
student1.setEnglish(80);
student1.setMaths(80);
student1.setJava(60);
System.out.println(student1+"���Գɼ���"+student1.testScore());
student student2=new studentxw("002","С��");
student2.setEnglish(80);
student2.setMaths(90);
student2.setJava(70);
((studentxw) student2).setDuty("�ලѧϰ");
System.out.println(student2+"���Գɼ���"+student2.testScore()+" ѧί��ְλ�ǣ� "+((studentxw) student2).getDuty());

student student3=new studentbz("003","С��");
student3.setEnglish(90);
student3.setMaths(80);
student3.setJava(80);
((studentbz) student3).setDuty("����༶");
System.out.println(student3+"���Գɼ���"+student3.testScore()+" �೤��ְλ�ǣ� "+((studentbz) student3).getDuty());
}
}