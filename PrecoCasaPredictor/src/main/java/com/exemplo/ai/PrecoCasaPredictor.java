package com.exemplo.ai;

import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import java.io.File;

public class PrecoCasaPredictor {

    public static void main(String[] args) throws Exception {
        RecordReader recordReader = new CSVRecordReader(1, ',');
        recordReader.initialize(new FileSplit(new File("src/main/resources/house_data.csv")));
        
        int numFeatures = 3;
        int labelIndex = 3;
        int batchSize = 7;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, labelIndex, true);
        DataSet allData = iterator.next();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .l2(0.001)
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numFeatures).nOut(5).activation(Activation.RELU).build())
                .layer(1, new DenseLayer.Builder().nIn(5).nOut(5).activation(Activation.RELU).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .nIn(5).nOut(1).activation(Activation.IDENTITY).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        int nEpochs = 500;
        System.out.println("Treinando o modelo...");
        for (int i = 0; i < nEpochs; i++) {
            model.fit(allData);
        }
        System.out.println("Treinamento finalizado!");

        INDArray input = Nd4j.create(new double[][]{{190, 3, 2}});
        INDArray prediction = model.output(input);

        double predictedPrice = prediction.getDouble(0);

        System.out.println("\n--- RESULTADO DA PREVISÃO ---");
        System.out.println("Características da casa: 190m², 3 quartos, 2 banheiros");
        System.out.printf("Preço previsto: R$ %.2f\n", predictedPrice);
    }
}
