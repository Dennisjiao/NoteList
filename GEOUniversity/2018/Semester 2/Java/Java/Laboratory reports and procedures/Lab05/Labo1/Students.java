package Students;
import java.io.*;
import java.util.Scanner;

public class Students {
	public static void main(String args[]) {
		Student stu[];
		StudentClass sClass = new StudentClass("软件   0201", 3);
		stu = new Student[sClass.getSize()];
		for (int i = 0; i < sClass.getSize(); i++) {
			stu[i] = new Student(getAStudent(i + 1));
		}
		sClass.setStu(stu);
		String sss = sClass.toString();
		System.out.println(sClass);
		for (int i = 0; i < 2; i++) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Please input the number you want:");
			String id1 = scanner.next();
			if (sClass.find(id1) < 0) {
				System.out.println("该学生不存在，请重新输入");
			} else {
				System.out.println("该同学存在");
			}
		}
	}

	public static Student getAStudent(int i) {
		Student studenti;
		Scanner scanner = new Scanner(System.in);
		String id = scanner.next();
		String name = scanner.next();
		int eScore = scanner.nextInt();
		int mScore = scanner.nextInt();
		int jScore = scanner.nextInt();
		studenti = new Student(id, name, eScore, mScore, jScore);
		return studenti;
	}
}

// Student.java
class Student implements Serializable {
	private String id;
	private String name;
	private int eScore;
	private int mScore;
	private int jScore;
	private int sum;

	public Student(String id, String name, int eScore, int mScore, int jScore) {
		this.id = id;
		this.name = name;
		this.eScore = eScore;
		this.mScore = mScore;
		this.jScore = jScore;
		sum();
	}

	public Student(Student s) {
		this.id = s.id;
		this.name = new String(s.name);
		this.eScore = s.eScore;
		this.mScore = s.mScore;
		this.jScore = s.jScore;
		sum();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int geteScore() {
		return eScore;
	}

	public void seteScore(int eScore) {
		this.eScore = eScore;
		sum();
	}

	public int getmScore() {
		return mScore;
	}

	public void setmScore(int mScore) {
		this.mScore = mScore;
		sum();
	}

	public int getjScore() {
		return jScore;
	}

	public void setjScore(int jScore) {
		this.jScore = jScore;
		sum();
	}

	public int getSum() {
		return sum;
	}

	// 计算总成绩
	void sum() {
		this.sum = eScore + mScore + jScore;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", eScore=" + eScore + ", mScore=" + mScore + ", jScore="
				+ jScore + ", sum=" + sum + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eScore;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + jScore;
		result = prime * result + mScore;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sum;
		return result;
	}

	@Override
	public boolean equals(Object x) {
		if (this.getClass() != x.getClass()) {
			return false;
		}
		Student b = (Student) x;
		return (this.getId().equals(b.getId()));
	}

	public int compare(Student A) {
		if (this.getSum() > A.getSum()) {
			return 1;
		} else if (this.getSum() == A.getSum()) {
			return 0;
		} else {
			return -1;
		}
	}
}

// StudentClass.java
class StudentClass {
	private String name; // 班级名称
	private int capacity = 40; // 最大容量
	private Student stu[]; // 班级类
	private int size; // 实际人数

	public StudentClass(String name, int size) {
		this.name = name;
		this.size = size;
		stu = new Student[capacity];
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public Student[] getStu() {
		return stu;
	}

	public int getSize() {
		return size;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setSize(int size) {
		if (size > capacity) {
			System.out.println("size为" + size + ",不能超过" + capacity);
			return;
		}
		this.size = size;

	}

	public void setStu(Student... stu) {
		for (int i = 0; i < size; i++) {
			this.stu[i] = new Student(stu[i]);
		}
		this.size = stu.length;
	}

	@Override
	public String toString() {
		String s;
		s = "班级：" + name + "\t" + "容量：" + capacity + "\t" + "实际人数：" + size + "\n\n";
		s = s + "学号" + "\t" + "姓名" + "\t" + "英语" + "\t" + "数学" + "\t" + "Java" + "\t" + "总成绩\n";
		for (int i = 0; i < size; i++) {
			s = s + stu[i].getId() + "\t" + stu[i].getName() + "\t" + stu[i].geteScore() + "\t" + stu[i].getmScore()
					+ "\t" + stu[i].getjScore() + "\t" + stu[i].getSum() + "\n";
		}
		return s;
	}

	// 查找操作
	public int find(String id) {
		for (int i = 0; i < size; i++) {
			if (stu[i].getId().equals(id)) {
				return i;
			}
		}
		return -1;
	}
}
