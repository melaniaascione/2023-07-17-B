package it.polito.tdp.gosales.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.gosales.dao.GOsalesDAO;

public class Model {
	
	private GOsalesDAO dao;
	private Graph<Products, DefaultWeightedEdge> grafo;
	private List<Products> vertici;
	private Map<Integer, Products> idMap;
	private List<Arco> archi;
	
	public Model() {
		this.dao = new GOsalesDAO();
		this.idMap = new HashMap<Integer, Products>();
	}
	
	
	
	//metodo per creare il grafo
	public void creaGrafo(String brand, int anno) { 
		this.grafo = new SimpleWeightedGraph<Products, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			
		//riempio l'identity map con i vertici
		this.vertici = dao.getVertici(brand);
		for(Products p : vertici) {
			this.idMap.put(p.getNumber(), p);
		}
					
		Graphs.addAllVertices(this.grafo, vertici);
		
		List<Arco> archi = this.dao.getArchi(brand, anno, idMap);
		for (Arco a : archi) {
			Products p1 = this.idMap.get(a.getpCode1());
			Products p2 = this.idMap.get(a.getpCode2());
			int peso = a.getPeso();
			Graphs.addEdgeWithVertices(this.grafo, p1, p2, peso);
		}
			
		System.out.println("Grafo creato con " + this.grafo.vertexSet().size() + " vertici e " + this.grafo.edgeSet().size() + " archi");
	}
	
	
	
	// metodo che mi restituisce i primi tre archi -> il peso è già ordinato perchè l'ho ordinato
	// nella query nel dao di getArchi
	public List<Arco> getTopArchi() {
		List<Arco> primiTre = new ArrayList<Arco>();
		for(Arco a : archi) {
			primiTre.add(a);
		}
		return primiTre.subList(0,3);
	}
	
	
	
	
	//metodo per restituire l'elenco dei vertici
	public List<Products> getVertici(String colore){
		return this.dao.getVertici(colore);
	}
	
	
	
	//metodo per restituire i brand del db
	public List<String> getBrand(){
		return this.dao.getBrand();
	}
	
	
	
	
	//metodo che mi restituisce il numero di vertici
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
		
	
	//metodo che mi restituisce il numero di archi
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
}
