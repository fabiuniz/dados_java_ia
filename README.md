<!-- 
  Tags: DadosIA
  Label: 🎲 Java com Dados e IA
  Description: Java com Dados e IA
  path_hook: hookfigma.hook1
-->

# Preço Casa Predictor - Quanto Vale Seu Imóvel

<p align="center">
  <img src="/images/screenshot.png" alt="imagem do Projeto">
</p>


Este projeto é uma aplicação Java que utiliza uma rede neural para prever o preço de casas com base em suas características. Ele demonstra a capacidade do Java para lidar com tarefas de Machine Learning e Inteligência Artificial(Nesse é uma IA de regressão, um tipo de IA discriminativa) através de bibliotecas como **Deeplearning4j** e **ND4J**.

## ⚙ Requisitos

Antes de começar, certifique-se de que você tem o seguinte instalado na sua máquina:

* **Java Development Kit (JDK) 11 ou superior**
* **Apache Maven**

## 🚀 Como Executar a Aplicação

Siga estes passos simples para compilar e rodar o projeto a partir da linha de comando:

1.  **Clone o repositório** (se ainda não o fez):
    ```bash
    git clone [https://github.com/fabiuniz/dados_java_ia.git](https://github.com/fabiuniz/dados_java_ia.git)
    cd seu-repositorio
    ```

2.  **Acesse a pasta do projeto Maven:**
    ```bash
    cd dados_ia/PrecoCasaPredictor
    ```

3.  **Compile o projeto com Maven:**
    Este comando vai baixar todas as dependências (as bibliotecas de IA) e compilar o código-fonte. O processo pode demorar um pouco na primeira vez.
    ```bash
    mvn clean install
    ```

4.  **Execute a aplicação:**
    Após a compilação, o arquivo JAR executável será criado na pasta `target`. Use o comando abaixo para rodar o programa.
    ```bash
    java -jar target/PrecoCasaPredictor-1.0-SNAPSHOT.jar
    ```

O terminal exibirá o processo de treinamento da rede neural e, em seguida, o resultado da previsão do preço de uma casa.

## 📁 Estrutura do Projeto

A estrutura de pastas segue o padrão de projetos Maven:
```bash
dados_ia/
├── PrecoCasaPredictor/
│   ├── pom.xml                   # Configurações do projeto e dependências (Maven)
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── exemplo/
│       │   │           └── ai/
│       │   │               └── PrecoCasaPredictor.java  # Lógica principal da IA
│       │   └── resources/
│       │       └── house_data.csv        # Dataset para o treinamento da IA
│
├── LICENSE
├── README.md
├── make.sh                       # Script para criar a estrutura do projeto
└── run.sh                        # Script para compilar e rodar a aplicação
```

## 🛠 Bibliotecas Utilizadas

* **Deeplearning4j (DL4J):** Framework de deep learning para Java.
* **ND4J:** Biblioteca de álgebra linear (equivalente ao NumPy para Java), otimizada para alto desempenho.
* **DataVec:** Ferramenta para processamento de dados e pipelines de Machine Learning.

## 👨‍💻 Autor

[Fabiano Rocha/Fabiuniz](https://github.com/SeuUsuarioGitHub)

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE)