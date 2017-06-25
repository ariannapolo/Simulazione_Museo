package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

public class Simulator {
	
	//parametri di simulazione
	private int INIZIALI;
	
	//informazioni che descrivono lo stato del sistema
	List<Student> students;
	SimpleDirectedGraph<Exhibition,DefaultEdge> graph;
	
	
	//coda degli eventi
	PriorityQueue<Evento> queue;
	
	public Simulator(int N, SimpleDirectedGraph<Exhibition,DefaultEdge> graph, Exhibition eStart){
		this.INIZIALI = N;
		this.graph = graph;
		this.queue = new PriorityQueue<>();
		this.students = new ArrayList<>();
		for(int i=0; i<INIZIALI;i++){
			students.add(new Student(i));
		}
		
		for(Student s: students){
			for(Integer i : eStart.getObjects()){
				s.addObject(i);
			}
			queue.add(new Evento(s,eStart,0));
		}
	}
	
	public List<Student> run(){
		while(!queue.isEmpty()){
			Evento e = queue.poll();
			List<DefaultEdge> l = new ArrayList<>(graph.outgoingEdgesOf(e.getExhibition()));
			if(l.size()!=0){
				Exhibition ex = graph.getEdgeTarget(l.get((int) (Math.random()*l.size())));
				System.out.println(ex);
				for(Integer i : ex.getObjects()){
					e.getStudent().addObject(i);
					System.out.println("Tempo: "+e.getTempo()+" num opere:"+e.getStudent().getNunOpere());
				}
				queue.add(new Evento(e.getStudent(),ex,(e.getTempo()+1)));
				
			}	
					
		}
		Collections.sort(students);
		return students;
	}
	

}
