package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Match, DefaultWeightedEdge> grafo;
	private List<Match> vertici = new ArrayList<>();
	private Map<Integer, Match> matchIdMap = new HashMap<>();

	
	public Model() {
		this.dao = new PremierLeagueDAO();
		
		
	}
	
	public void creaGrafo(String mese, int min) {
		
		this.grafo = new SimpleWeightedGraph<Match, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		this.vertici = dao.getVertici(mese);
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Match m : vertici) {
			this.matchIdMap.put(m.getMatchID(), m);
		}
		
		aggiungiArchi(min);
		
	}
	
	public void aggiungiArchi(int min) {
		
		List<Arco> archi = this.dao.getArchi(min);
		
		
		for(Arco a : archi) {
		
			if(this.vertici.contains(this.matchIdMap.get(a.getT1())) && this.vertici.contains(this.matchIdMap.get(a.getT2()))) {

				Graphs.addEdgeWithVertices(this.grafo, this.matchIdMap.get(a.getT1()), this.matchIdMap.get(a.getT2()), a.getPeso());
			}
			
		}
	}
	
	public List<ConnMax> getConnessioneMassima(){
		
		List<ConnMax> result = new ArrayList<>();
		
		double best = -1;
		
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			
			double peso = this.grafo.getEdgeWeight(e);
			
			if(peso > best) {
				result.clear();
				best = peso;
				result.add(new ConnMax(this.grafo.getEdgeTarget(e), this.grafo.getEdgeSource(e), peso));
			}
			else if(peso == best) {
				result.add(new ConnMax(this.grafo.getEdgeTarget(e), this.grafo.getEdgeSource(e), peso));
			}
					
					
		}
		
		return result;
		
		
	}
	
	public String infoGrafo() {
		return "Grafo creato!\n#Vertici: "+this.grafo.vertexSet().size()+"\n#Archi: "+this.grafo.edgeSet().size();
	}

	public List<Match> getVertici() {
		return vertici;
	}

	public Graph<Match, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	
	
	
}
