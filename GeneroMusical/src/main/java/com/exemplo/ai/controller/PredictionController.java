package com.exemplo.ai;



import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/previsao")
public class PredictionController {

    private final MultiLayerNetwork model;
    private final String[] generos = {"Pop", "Rock", "Jazz"};

    // Construtor que recebe o modelo treinado
    public PredictionController(MultiLayerNetwork model) {
        this.model = model;
    }

    // Estrutura para receber os dados do front-end
    public static class MusicFeatures {
        public double intervaloMedio;
        public double ritmoMedio;
        public double complexidadeRitmo;
        public double tonicaIndex;
    }

    // Endpoint para fazer a previsão
    @PostMapping
    public ResponseEntity<String> predict(@RequestBody MusicFeatures features) {
        
        // Sua lógica de previsão (sem alterações)
        INDArray inputMusica = Nd4j.create(new double[][]{
            {features.intervaloMedio, features.ritmoMedio, features.complexidadeRitmo, features.tonicaIndex}
        });
        INDArray outputPrevisao = model.output(inputMusica);
        int classePrevista = Nd4j.argMax(outputPrevisao, 1).getInt(0);
        String generoPrevisto = generos[classePrevista];

        // --- Lógica para SALVAR os dados no CSV ---
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/music_data.csv", true))) {
            // A linha abaixo salva apenas as características, sem o gênero previsto.
            // Para salvar com o gênero, você precisaria de um input extra no formulário.
            writer.println(
                String.format("%.1f,%.0f,%.1f,%.0f", 
                features.intervaloMedio, 
                features.ritmoMedio, 
                features.complexidadeRitmo, 
                features.tonicaIndex)
            );
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo CSV: " + e.getMessage());
        }
        // --- Fim da lógica de salvamento ---

        return ResponseEntity.ok("{\"generoPrevisto\":\"" + generoPrevisto + "\"}");
    }
}