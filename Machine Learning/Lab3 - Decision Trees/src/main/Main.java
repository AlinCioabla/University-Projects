package main;

import weka.classifiers.Classifier;
import weka.classifiers.trees.Id3;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

class Main {
    public static void main(String args[]) {
        try {
            DatasetDependencies datasetDependecies = new DatasetDependencies();
            Instances trainingDataSet = datasetDependecies.getDataSet(DatasetDependencies.TRAINING_DATA_SET_FILENAME);
            Instances testingDataSet = datasetDependecies.getDataSet(DatasetDependencies.TESTING_DATA_SET_FILENAME);

            System.out.println("************************** J48 *************************");
            J48 j48Classifier = new J48();
            j48Classifier.buildClassifier(trainingDataSet);
            Evaluation eval = new Evaluation(trainingDataSet);
            eval.evaluateModel(j48Classifier, testingDataSet);
            TreeVisualizer j48TreeVisualizer = new TreeVisualizer(null, j48Classifier.graph(), new PlaceNode2());
            datasetDependecies.viewDecisionTree(j48TreeVisualizer, "J48");
            System.out.println("** Decision Tress Evaluation with Datasets **");
            System.out.println(eval.toSummaryString());
            System.out.print(" the expression for the input data as per alogorithm is ");
            System.out.println(j48Classifier);
            System.out.println(eval.toMatrixString());
            System.out.println(eval.toClassDetailsString());

            System.out.println("************************** ID3 *************************");
            Id3 id3Classifier = new Id3();
            id3Classifier.buildClassifier(trainingDataSet);
            Evaluation evalId3 = new Evaluation(trainingDataSet);
            evalId3.evaluateModel(id3Classifier, testingDataSet);
            System.out.println("** Decision Tress Evaluation with Datasets **");
            System.out.println(evalId3.toSummaryString());
            System.out.print(" the expression for the input data as per algorithm is ");
            System.out.println(id3Classifier);
            System.out.println(evalId3.toMatrixString());
            System.out.println(evalId3.toClassDetailsString());


            Instances unlabeled = new Instances(new BufferedReader(new FileReader("C:\\Users\\Alin Cioabla\\Documents\\University-Projects\\Machine Learning\\Lab3 - Decision Trees\\src\\HR-Em.arff")));
            unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
            Instances labeled = new Instances(unlabeled);

            for (int i = 0; i < unlabeled.numInstances(); i++) {
                System.out.println(unlabeled.instance(i));
                double clsLabel = j48Classifier.classifyInstance(unlabeled.instance(i));
                labeled.instance(i).setClassValue(clsLabel);
            }
            System.out.println(labeled.toString());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}