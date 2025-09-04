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
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class ModelService {

    private MultiLayerNetwork model;

    @PostConstruct
    public void init() throws Exception {
        trainModel();
    }

    public void trainModel() throws Exception {
        System.out.println("Iniciando o treinamento do modelo...");

        File fileMusica = new File("src/main/resources/music_data.csv");
        if (!fileMusica.exists()) {
            throw new RuntimeException("Arquivo music_data.csv não encontrado.");
        }

        RecordReader recordReader = new CSVRecordReader(1, ',');
        recordReader.initialize(new FileSplit(fileMusica));

        int numFeatures = 4;
        int labelIndex = 4;
        int numClasses = 3;
        int batchSize = 10;
        int nEpochs = 500;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123).l2(0.001).activation(Activation.RELU).weightInit(WeightInit.XAVIER).list()
                .layer(0, new DenseLayer.Builder().nIn(numFeatures).nOut(15).build())
                .layer(1, new DenseLayer.Builder().nIn(15).nOut(15).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(15).nOut(numClasses).activation(Activation.SOFTMAX).build())
                .build();

        this.model = new MultiLayerNetwork(conf);
        this.model.init();

        for (int i = 0; i < nEpochs; i++) {
            this.model.fit(allData);
        }

        System.out.println("Treinamento de classificação de gênero concluído!");
    }

    public MultiLayerNetwork getModel() {
        return model;
    }
}