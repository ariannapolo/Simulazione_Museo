package it.polito.tdp.artsmia.model;

public class Evento implements Comparable<Evento> {
	private Student student;
	private Exhibition exhibition;
	private int t;
	
	
	public Evento(Student student, Exhibition exhibition, int tempo) {
		super();
		this.student = student;
		this.exhibition = exhibition;
		this.t = tempo;
	}


	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}


	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}


	/**
	 * @return the exhibition
	 */
	public Exhibition getExhibition() {
		return exhibition;
	}


	/**
	 * @param exhibition the exhibition to set
	 */
	public void setExhibition(Exhibition exhibition) {
		this.exhibition = exhibition;
	}


	/**
	 * @return the tempo
	 */
	public int getTempo() {
		return t;
	}


	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(int tempo) {
		this.t = tempo;
	}


	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return this.t-o.t;
	}
	
	
	
	

}
