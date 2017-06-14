package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.List;

public class Exhibition {
	
	private int id;
	private String department;
	private String title;
	private int begin;
	private int end;
	private int numObject;
	private List<Integer> objectsId;
	
	public Exhibition(int id, String department, String title, int begin, int end) {
		super();
		this.id = id;
		this.department = department;
		this.title = title;
		this.begin = begin;
		this.end = end;
		this.objectsId = new ArrayList<>();
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	
	public int getNumObject() {
		return numObject;
	}

	public void setNumObject(int numObject) {
		this.numObject = numObject;
	}
	public void addObject(int id){
		this.objectsId.add(id);
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
		Exhibition other = (Exhibition) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return title+" "+begin+"-"+end;
	}
	
	

}
