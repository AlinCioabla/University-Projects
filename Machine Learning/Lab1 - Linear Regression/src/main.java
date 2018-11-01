
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class main {

    public static final String DATASETPATH = "C:\\Users\\Alin Cioabla\\Desktop\\Machine-Learning-First-Homework\\Machine Learning - First Homework\\abalone.arff";
    public static final String MODElPATH = "C:\\Users\\Alin Cioabla\\Desktop\\Machine-Learning-First-Homework\\Machine Learning - First Homework\\model.bin";

    public static void main(String[] args) throws Exception{
	    DataSource source = new DataSource(DATASETPATH);
		Instances dataset = source.getDataSet();
		dataset.setClassIndex(dataset.numAttributes()-1);

		LinearRegression lr = new LinearRegression();
		lr.buildClassifier(dataset);

		Evaluation lreval = new Evaluation(dataset);
		lreval.evaluateModel(lr, dataset);
		System.out.println(lreval.toSummaryString());

		
	}
}