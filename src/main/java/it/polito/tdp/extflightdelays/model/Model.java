package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private Graph<Airport, DefaultWeightedEdge> grafo;
	List<Flight> listaVoli = new ArrayList<Flight>();
	List<Airport> listaAeroporti = new ArrayList<Airport>();
	ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
	
	public List<Flight> getAllFlights() {
		this.listaVoli = this.dao.loadAllFlights();
		return this.listaVoli;
	}
	
	public List<Airport> getAllAirport() {
		this.listaAeroporti = this.dao.loadAllAirports();
		return this.listaAeroporti;
	}
	
	public Airport getAirportbyId(int id) {
		return this.getAirportbyId(id);
	}
	
	public List<CoppiaAirport> getAllCoppieAirport(int distMin){
		return this.dao.getAllCoppiaAirports(distMin);
	}

	public void creaGrafo(int distanzaMinima) {
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, this.getAllAirport());

//		for(Airport a : listaAeroporti) {
//			for(Airport a1 : this.dao.getAeroportiConnessi(a)) {
//				if(!this.grafo.containsEdge(a, a1) || !this.grafo.containsEdge(a1, a)) {
//					int q = distanzaMedia(this.dao.getAllFlightsByAirports(a, a1));
//					if(q>=distanzaMinima) {
//						DefaultWeightedEdge e = this.grafo.addEdge(a, a1);
//						this.grafo.setEdgeWeight(e, q);
//						
//					}					
//				}
//			}
//		}
		
		for(CoppiaAirport c : this.getAllCoppieAirport(distanzaMinima)) {
			Airport a = this.listaAeroporti.get(c.getaPartenzaId());
			Airport a1 = this.listaAeroporti.get(c.getaArrivoId());
			if(!this.grafo.containsEdge(a, a1)) {
				this.grafo.addEdge(a, a1);
				this.grafo.setEdgeWeight(a, a1, c.getAvgDist());
			}
		}
		
		System.out.println("Vertici = " + this.grafo.vertexSet().size());
		System.out.println("Archi = " + this.grafo.edgeSet().size());
		System.out.println("\n--------------------------\n");	
//		System.out.println(this.grafo.toString());			
		for(DefaultWeightedEdge e : this.grafo.edgeSet())
			System.out.println("Arco " + e + " con peso " + this.grafo.getEdgeWeight(e));
		
		
	}

	private int distanzaMedia(List<Flight> lista) {
		int dist = 0;
		for(Flight f : lista) {
			dist+=f.getDistance();
		}
		return dist/lista.size();
	}
}
