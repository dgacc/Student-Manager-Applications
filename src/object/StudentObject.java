package object;

public class StudentObject extends PersonObject{
	private int idStudent;
	private String course;
	private String specialized;
	private String classSt;
	private int educate;
	private String note;
	
	public int getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSpecialized() {
		return specialized;
	}

	public void setSpecialized(String nganhhoc) {
		this.specialized = nganhhoc;
	}
	
	public String getClassSt() {
		return classSt;
	}
	public void setClassSt(String lop) {
		this.classSt = lop;
	}

	public int getEducate() {
		return educate;
	}
	public void setEducate(int hedaotao) {
		this.educate = hedaotao;
	}
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
