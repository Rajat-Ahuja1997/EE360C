
public class result {
	private int hour;
	private int grade;
	public int getHour() {
		return hour;
	}

	public boolean equals(Object obj) {
		int a=0;
		if (obj instanceof result){
			result x = (result) obj;

			if(hour == x.getHour() &&
					grade == x.getGrade())
				return true;
		}

		return false;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getGrade() {
		return grade;
	}
	public result(int hour, int grade) {
		super();
		this.hour = hour;
		this.grade = grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}



}
