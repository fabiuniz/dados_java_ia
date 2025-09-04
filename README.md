<!-- 
  Tags: DadosIA
  Label: 🎲 Java com Dados e IA
  Description: Java com Dados e IA
  path_hook: hookfigma.hook1
-->

# Java com Dados e IA

<p align="center">
  <img src="/images/screenshot.png" alt="imagem do Projeto">
</p>

Este repositório contém dois projetos Java que demonstram o uso de Inteligência Artificial e Machine Learning por meio das bibliotecas **Deeplearning4j** e **ND4J**. Cada projeto é uma aplicação completa, focada em um problema de IA distinto: um para **regressão** (previsão de preço) e outro para **classificação** (previsão de gênero musical).

---

### PrecoCasaPredictor e MusicGenreClassifier: Dois Exemplos de IA em Java

Ambos os projetos são aplicações Java que utilizam redes neurais para tarefas de Machine Learning. Eles demonstram a capacidade do Java para lidar com problemas complexos de IA de forma eficiente.

#### PrecoCasaPredictor

Este projeto é uma **IA de Regressão**. Ele usa uma rede neural para **prever o preço de um imóvel** com base em suas características, como área em metros quadrados, número de quartos e banheiros. É uma demonstração prática de como a IA pode resolver problemas de previsão contínua.

* **Problema:** Previsão de valor (o preço é um valor contínuo).
* **Modelo de IA:** Rede neural de regressão.
* **Dados:** `house_data.csv` (Área, Quartos, Banheiros, Preço).
* **Uso:** Você pode inserir as características de uma casa e o modelo irá estimar seu valor.

#### MusicGenreClassifier

Este projeto é uma **IA de Classificação**. Sua função é **categorizar uma música em um gênero** específico (Pop, Rock ou Jazz) a partir de suas características musicais, como ritmo e complexidade. Ele ilustra como a IA pode classificar dados em categorias discretas.

* **Problema:** Classificação de categorias (o gênero é um valor discreto).
* **Modelo de IA:** Rede neural de classificação.
* **Dados:** `music_data.csv` (Intervalo Médio, Ritmo Médio, Complexidade do Ritmo, Tônica, Gênero).
* **Uso:** Ao fornecer as características de uma música, o modelo irá prever o gênero mais provável.

---

## ⚙ Requisitos

Antes de começar, certifique-se de que você tem o seguinte instalado na sua máquina:

* **Java Development Kit (JDK) 11 ou superior**
* **Apache Maven**

## 🚀 Como Executar a Aplicação

Este repositório contém scripts (`run_predicator.sh` e `run_music.sh`) para simplificar a execução de cada projeto.

1.  **Clone o repositório** (se ainda não o fez):
    ```bash
    git clone [https://github.com/fabiuniz/dados_java_ia.git](https://github.com/fabiuniz/dados_java_ia.git)
    cd seu-repositorio
    ```

2.  **Acesse a pasta principal do projeto:**
    ```bash
    cd dados_java_ia/
    ```

3.  **Execute o projeto desejado:**

    * Para o **PrecoCasaPredictor**:
        ```bash
        ./run_predicator.sh
        ```
    * Para o **MusicGenreClassifier**:
        ```bash
        ./run_music.sh
        ```

    O script irá compilar e rodar o projeto automaticamente. A saída do terminal mostrará o processo de treinamento e o resultado da previsão.

---

## 📁 Estrutura do Projeto

A estrutura de pastas foi atualizada para organizar os dois projetos em um único repositório, seguindo o padrão Maven:

```bash
dados_java_ia/
├── GeneroMusical/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/exemplo/ai/MusicGenreClassifier.java # Lógica principal da IA
│           └── resources/
│               └── music_data.csv                           # Dataset para o treinamento
│
├── PrecoCasaPredictor/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/exemplo/ai/PrecoCasaPredictor.java   # Lógica principal da IA
│           └── resources/
│               └── house_data.csv                           # Dataset para o treinamento
│
├── LICENSE
├── README.md
├── images/
│   └── screenshot.png
├── run_music.sh                                             # Script para compilar e rodar MusicGenreClassifier
└── run_predicator.sh                                        # Script para compilar e rodar PrecoCasaPredictor
```

### PrecoCasaPredictor e MusicGenreClassifier: Dois Exemplos de IA em Java

Este repositório apresenta dois projetos distintos para demonstrar o poder de **Java para Inteligência Artificial** usando as bibliotecas **Deeplearning4j** e **ND4J**. Cada projeto é uma aplicação de Machine Learning completa, focada em um tipo de problema diferente.

---


## 🛠 Bibliotecas Utilizadas

* **Deeplearning4j (DL4J):** Framework de deep learning para Java.
* **ND4J:** Biblioteca de álgebra linear (equivalente ao NumPy para Java), otimizada para alto desempenho.
* **DataVec:** Ferramenta para processamento de dados e pipelines de Machine Learning.

## 👨‍💻 Autor

[Fabiano Rocha/Fabiuniz](https://github.com/SeuUsuarioGitHub)

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE)