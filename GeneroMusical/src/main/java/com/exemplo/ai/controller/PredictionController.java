package com.exemplo.ai;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final String[] generos = {"Pop", "Rock", "Jazz"};

    @Autowired
    private ModelService modelService; // O controlador agora depende do serviço

    // Endpoint para fazer a previsão
    @PostMapping("/previsao")
    public ResponseEntity<String> predict(@RequestBody MusicFeatures features) {
        MultiLayerNetwork model = modelService.getModel(); // Pega o modelo do serviço
        
        // Lógica de previsão
        INDArray inputMusica = Nd4j.create(new double[][]{
            {features.intervaloMedio, features.ritmoMedio, features.complexidadeRitmo, features.tonicaIndex}
        });
        INDArray outputPrevisao = model.output(inputMusica);
        int classePrevista = Nd4j.argMax(outputPrevisao, 1).getInt(0);
        String generoPrevisto = generos[classePrevista];

        // Lógica para salvar os dados no CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/music_data.csv", true))) {
            writer.println(
                String.format("%.1f,%.0f,%.1f,%.0f", 
                features.intervaloMedio, 
                features.ritmoMedio, 
                features.complexidadeRitmo, 
                features.tonicaIndex)
            );
            System.out.println("Dados de música salvos no CSV: " + features.intervaloMedio + ", " + features.ritmoMedio);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }

        return ResponseEntity.ok("{\"generoPrevisto\":\"" + generoPrevisto + "\"}");
    }

    // NOVO ENDPOINT PARA TREINAMENTO
    @PostMapping("/re-treinar")
    public ResponseEntity<String> reTrainModel() {
        System.out.println("Recebida requisição para re-treinar o modelo. Aguarde...");
        try {
            modelService.trainModel(); // Chama o método do serviço
            return ResponseEntity.ok("{\"status\":\"Modelo re-treinado com sucesso!\"}");
        } catch (Exception e) {
            System.err.println("Erro durante o re-treinamento: " + e.getMessage());
            return ResponseEntity.status(500).body("{\"status\":\"Erro ao re-treinar o modelo.\"}");
        }
    }

    public static class MusicFeatures {
        public double intervaloMedio;
        public double ritmoMedio;
        public double complexidadeRitmo;
        public double tonicaIndex;
    }
}