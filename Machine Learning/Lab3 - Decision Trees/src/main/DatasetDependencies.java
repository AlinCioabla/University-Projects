package main;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

class DatasetDependencies {
    static final String TRAINING_DATA_SET_FILENAME = "C:\\Users\\Alin Cioabla\\Documents\\University-Projects\\Machine Learning\\Lab3 - Decision Trees\\src\\HR-Em.arff";
    static final String TESTING_DATA_SET_FILENAME = "C:\\Users\\Alin Cioabla\\Documents\\University-Projects\\Machine Learning\\Lab3 - Decision Trees\\src\\HR-Em.arff";

    Instances getDataSet(String fileName) throws IOException {

        int classIdx = 15;
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(fileName));
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(classIdx);
        return dataSet;
    }

    void viewDecisionTree(TreeVisualizer treeVisualizer, String algorithm) {
        JFrame jf = new JFrame("Weka Classifier Tree Visualizer: " + algorithm);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(1980, 1080);
        jf.getContentPane().setLayout(new BorderLayout());
        jf.getContentPane().add(treeVisualizer, BorderLayout.CENTER);
        jf.setVisible(true);
        treeVisualizer.fitToScreen();
    }
}
