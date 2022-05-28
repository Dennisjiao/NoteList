package student;

public class Test {
	public static void main(String[] args) {
	student student1=new student("001","李华");
	student1.setEnglish(80);
	student1.setMaths(80);
	student1.setJava(60);
	System.out.println(student1+"测试成绩是"+student1.testScore());
	student student2=new studentxw("002","李明");
	student2.setEnglish(80);
	student2.setMaths(90);
	student2.setJava(70);
	System.out.println(student2+"测试成绩是"+student2.testScore());
	student student3=new studentbz("003","小明");
	student3.setEnglish(90);
	student3.setMaths(80);
	student3.setJava(80);
	System.out.println(student3+"测试成绩是"+student3.testScore());
	student test[]= new student[5];
	test[0] = new student("004","小王");
	test[0].setEnglish(80);
	test[0].setMaths(90);
	test[0].setJava(70);
	test[1] = new student("005","LiMing");
	test[1].setEnglish(80);
	test[1].setMaths(50);
	test[1].setJava(70);
	test[2] = new student("006","Jerry");
	test[2].setEnglish(80);
	test[2].setMaths(80);
	test[2].setJava(70);
	test[3] = new studentbz("007","Tom");
	test[3].setEnglish(80);
	test[3].setMaths(90);
	test[3].setJava(100);
	test[4] = new studentxw("008","danny");
	test[4].setEnglish(100);
	test[4].setMaths(90);
	test[4].setJava(70);
	System.out.println(test[0]+"测试成绩是"+test[0].testScore());
	System.out.println(test[1]+"测试成绩是"+test[1].testScore());
	System.out.println(test[2]+"测试成绩是"+test[2].testScore());
	System.out.println(test[3]+"测试成绩是"+test[3].testScore());
	System.out.println(test[4]+"测试成绩是"+test[4].testScore());
	}
}