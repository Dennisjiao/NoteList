package student;

class studentbz extends student{

	public studentbz(String id, String name, int english, int maths, int java,
			int sum) {
		super(id, name, english, maths, java, sum);
		
	}
	public studentbz(String id, String name) {
		 super(id,name);
		 }
	private String duty;
	 public String getDuty() {
	 return duty;
	 }
	 public void setDuty(String duty) {
	 this.duty = duty;
	 }
	 public double testScore() {
		 return super.testScore()+1;
		 }


}