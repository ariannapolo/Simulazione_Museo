package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;


public class Model {
	private SimpleDirectedGraph<Exhibition,DefaultEdge> graph;
	private Map<Integer,Exhibition> exhibitions = new HashMap<>();
	
	public List<Integer> getYears(){
		ArtsmiaDAO dao = new ArtsmiaDAO();
		
		return dao.getYearsExhibitions();
	}
	
	public Collection<Exhibition> getExhibition(int anno){
		ArtsmiaDAO dao = new ArtsmiaDAO();
		for(Exhibition e :  dao.getExhibition(anno)){
			exhibitions.put(e.getId(), e);
		}
		return exhibitions.values();
		
	}
	
	public void creaGrafo(int anno){
		graph = new SimpleDirectedGraph<Exhibition,DefaultEdge>(DefaultEdge.class);
		
		Graphs.addAllVertices(graph, this.getExhibition(anno));
		
		for(Exhibition e1 : graph.vertexSet()){
			for(Exhibition e2 : graph.vertexSet()){
				if(!e1.equals(e2) && e1.getBegin()<e2.getBegin() && e1.getEnd()>=e2.getBegin() && e1.getEnd()<=e2.getEnd()){
					graph.addEdge(e1, e2);
				}
			}
			
		}
	//	System.out.println(graph);
		
		
	}
	
	public boolean graphIsConnected(){
		ConnectivityInspector<Exhibition,DefaultEdge> ci = new ConnectivityInspector<>(graph);
		if(ci.isGraphConnected())
			return true;
		return false;
	}
	
	public Exhibition getMaxOggetti(){
		ArtsmiaDAO dao = new ArtsmiaDAO();
		dao.setOggetti(exhibitions);
		return dao.numOggettiMax(exhibitions);
		
	}
	
	public List<Student> visite(int N, int anno){
		List<Exhibition> exhibitionAnno = new ArrayList<>();
		for(Exhibition e : graph.vertexSet()){
			if(e.getBegin()==anno){
				exhibitionAnno.add(e);
			}
		}
		Simulator s = new Simulator(N,graph,exhibitionAnno.get((int) (1+Math.random()*exhibitionAnno.size())));
		return s.run();
	}
	
	
	
	public static void main(String arg[]){
		Model model = new Model();
		model.creaGrafo(2010);
		System.out.println(model.getMaxOggetti());
		for(Student s : model.visite(10, 2015) ){
			System.out.println(s);
		}
		
	}

}
