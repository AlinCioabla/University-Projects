package main;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Main {

	public static void main(String[] args) throws Exception {

		ConverterUtils.DataSource source = new ConverterUtils.DataSource(
				"C:\\Program Files\\Weka-3-8\\data\\cpu.arff");
		Instances dataset = source.getDataSet();

		ClusterEvaluation eval = new ClusterEvaluation();

		SimpleKMeans kMeans = new SimpleKMeans();
		kMeans.setNumClusters(3);
		kMeans.setDistanceFunction(new weka.core.ManhattanDistance());
		kMeans.buildClusterer(dataset);
		eval.setClusterer(kMeans);
		eval.evaluateClusterer(dataset);
		System.out.println(eval.clusterResultsToString());

		EM em = new EM();
		em.setNumClusters(3);
		em.buildClusterer(dataset);
		eval.setClusterer(em);
		eval.evaluateClusterer(dataset);
		System.out.println(eval.clusterResultsToString());

		HierarchicalClusterer hierarchicalClusterer = new HierarchicalClusterer();
		hierarchicalClusterer.setNumClusters(3);
		hierarchicalClusterer.buildClusterer(dataset);
		eval.setClusterer(hierarchicalClusterer);
		eval.evaluateClusterer(dataset);
		System.out.println(eval.clusterResultsToString());

	}
}
