package it.polito.tdp.extflightdelays.model;

import java.util.*;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.CoppiaAirports;
import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model 
{
	private Graph<String,DefaultWeightedEdge> grafo;
	private ExtFlightDelaysDAO dao;
	public String creaGrafo()
	{
		this.dao=new ExtFlightDelaysDAO();
		this.grafo=new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);
		List<String> list=this.dao.loadAllStates();
		Graphs.addAllVertices(this.grafo, list);
		List<CoppiaAirports> listaCoppie=dao.loadAllCouples();
		for(CoppiaAirports c:listaCoppie)
		{
			Graphs.addEdgeWithVertices(this.grafo, c.getA1(), c.getA2(), c.getPeso());
		}
		return String.format("Grafo creato! #vertici %d, # Archi %d\n", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
	}
	public List<String> getStati()
	{
		return this.dao.loadAllStates();
	}
	public List<CoppiaAirports>visualizzaVelivoli(String partenza)
	{
		List<String> successori=Graphs.successorListOf(this.grafo, partenza);
		List<CoppiaAirports> coppie=new ArrayList<>();
		for(String s:successori)
		{
			coppie.add(new CoppiaAirports(partenza,s,(int)this.grafo.getEdgeWeight(this.grafo.getEdge(partenza, s))));
		}
		Collections.sort(coppie);
		return coppie;
	}
}
