package it.polito.tdp.artsmia.model;

import java.util.HashSet;
import java.util.Set;

public class Student implements Comparable<Student> {

	private int id;
	private Set<Integer> opereId;
	
	public Student(int id){
		this.id = id;
		this.opereId = new HashSet<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNunOpere() {
		return opereId.size();
	}
	public Set<Integer> getOpere() {
		return opereId;
	}


	public void addObject(int id){
		opereId.add(id);
	}

	@Override
	public int compareTo(Student o) {
		
		return this.opereId.size()-o.opereId.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id+" "+opereId.size();
	}
	
	
	
}
