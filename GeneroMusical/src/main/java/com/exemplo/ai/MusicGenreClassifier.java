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

// A classe agora tem um nome que faz sentido para o projeto
public class MusicGenreClassifier {

    public static void main(String[] args) throws Exception {

        File fileMusica = new File("src/main/resources/music_data.csv");
        if (!fileMusica.exists()) {
            System.err.println("Arquivo music_data.csv não encontrado. Crie-o na pasta src/main/resources/.");
            System.err.println("Sua estrutura deve ser: Intervalo_Medio,Ritmo_Medio,Complexidade_Ritmo,Tonica_Index,Genero");
            return;
        }

        RecordReader recordReader = new CSVRecordReader(1, ',');
        recordReader.initialize(new FileSplit(fileMusica));

        // Número de características da música
        int numFeatures = 4;
        
        // Índice da coluna de gênero (a classe a ser prevista)
        int labelIndex = 4;
        
        // Número de gêneros para classificar
        int numClasses = 3; 
        int batchSize = 10;

        DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
        DataSet allData = iterator.next();
        allData.shuffle();

        System.out.println("Dados de música carregados. Treinando o modelo para classificação de gênero.");

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .l2(0.001)
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .list()
                .layer(0, new DenseLayer.Builder().nIn(numFeatures).nOut(15).build())
                .layer(1, new DenseLayer.Builder().nIn(15).nOut(15).build())
                .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(15).nOut(numClasses).activation(Activation.SOFTMAX).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        int nEpochs = 500;
        System.out.println("Iniciando o treinamento...");
        for (int i = 0; i < nEpochs; i++) {
            model.fit(allData);
        }
        System.out.println("Treinamento de classificação de gênero concluído!");

        // --- Demonstração de Previsão de Gênero ---
        System.out.println("\n--- ANÁLISE DE UMA NOVA MÚSICA ---");
        
        // Características de uma nova música (exemplo de Rock):
        // Intervalo_Medio, Ritmo_Medio_BPM, Complexidade_Ritmo, Tonica_Index
        INDArray inputMusica = Nd4j.create(new double[][]{{3.8, 145, 0.8, 4}});
        INDArray outputPrevisao = model.output(inputMusica);

        System.out.println("Probabilidades de gênero: " + outputPrevisao);

        int classePrevista = Nd4j.argMax(outputPrevisao, 1).getInt(0);
        String[] generos = {"Pop", "Rock", "Jazz"};
        String generoPrevisto = generos[classePrevista];

        System.out.println("Gênero previsto: " + generoPrevisto);
    }
}