package student;

class student{ 
	   public student(String id, String name) {
			this.id = id;
			this.name = name;
			}
	   public student() {
			 }
		 public student(String id, String name, int english, int maths, int java, int sum){ 
		  this.id = id; 
		  this.name = name; 
		  this.english = english; 
		  this.maths = maths; 
		  this.java = java; 
		  this.sum = sum; 
	 } 
			 
		
		private String id;
		private String name;
		private int english;
		private int maths;
		private int java; 
		private int sum;
	 public String getid() {
		 return id;
		 }
		 public void setid(String id) {
		 this.id = id;
		 }
		 public String getName() {
		 return name;
		 }
		 public void setName(String name) {
		 this.name = name;
		 }
		 public double getEnglish() {
		 return english;
		 }
		 public void setEnglish(int english) {
		 this.english = english;
		 sum+=english;
		 }
		 public int getMaths() {
		 return maths;
		 }
		 public void setMaths(int maths) {
		 this.maths = maths;
		 sum+=maths;
		 }
		 public int getJava() {
		 return java;
		 }
		 public void setJava(int java) {
		 this.java = java;
		 sum+=java;
		 }
		 public int getSum() {
		 return sum;
		 }
		 public void setSum(int sum) {
		 this.sum = sum;
		 }
		 public String toString() {
		 return "学号："+id+" 姓名 "+name+" 英语 "+english+" 数学 "+maths+" Java "+java+" 总成绩  "+sum ;
		 }
		 public boolean equals(Object x){  
			  if(this.getClass() != x.getClass() ){  
			   return false;  
			  }  
			  student b = (student) x;  
			  return (this.getid().equals(b.getid())); 
			  } 
		 public int compare(student A){  
			   if(this.getSum() > A.getSum()){  
			    return 0;  
			   }else{  
			    return -1;  
			   }  
			  } 
		 public int sum(){
		 return english+maths+java;
		 }
		 public double testScore(){
		 return sum/3;
		 }
		}





