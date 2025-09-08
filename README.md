<!-- 
  Tags: DadosIA
  Label: 🎲 Java com Dados e IA
  Description:⭐ Java com Dados e IA
  path_hook: hookfigma.hook1
-->

# Java com Dados e IA

<p align="center">
  <img src="/images/screenshot.png" alt="imagem do Projeto">
</p>

Este repositório contém um projeto Java que demonstra o uso de Inteligência Artificial e Machine Learning por meio das bibliotecas **Deeplearning4j** e **ND4J**. A aplicação, um classificador de gênero musical, agora inclui um **frontend** para que você possa ver de forma prática como a IA funciona.

---

### MusicGenreClassifier: IA de Classificação com Frontend

Este projeto é uma **IA de Classificação**. Sua função é **categorizar uma música em um gênero** específico (Pop, Rock ou Jazz) a partir de suas características musicais. A grande novidade é a inclusão de um frontend simples, que permite a visualização interativa do resultado da classificação, tornando o funcionamento da IA mais claro e acessível.

* **Problema:** Classificação de categorias (o gênero é um valor discreto).
* **Modelo de IA:** Rede neural de classificação.
* **Dados:** `music_data.csv` (Intervalo Médio, Ritmo Médio, Complexidade do Ritmo, Tônica, Gênero).
* **Uso:** Você pode interagir com o frontend, fornecer as características de uma música e o modelo irá prever o gênero mais provável.

---

## ⚙ Requisitos

Antes de começar, certifique-se de que você tem o seguinte instalado na sua máquina:

* **Java Development Kit (JDK) 11 ou superior**
* **Apache Maven**

## 🚀 Como Executar a Aplicação

Este repositório contém um script (`run_music.sh`) para simplificar a execução do projeto.

1.  **Clone o repositório** (se ainda não o fez):
    ```bash
    git clone [https://github.com/fabiuniz/dados_java_ia/tree/FrontEnd](https://github.com/fabiuniz/dados_java_ia/tree/FrontEnd)
    cd dados_java_ia
    ```

2.  **Execute o projeto:**
    ```bash
    ./run_music.sh
    ```
    Após a execução, acesse `http://localhost:8080/` no seu navegador para interagir com o frontend.

---

## 🛠 Bibliotecas Utilizadas

* **Deeplearning4j (DL4J):** Framework de deep learning para Java.
* **ND4J:** Biblioteca de álgebra linear (equivalente ao NumPy para Java), otimizada para alto desempenho.
* **DataVec:** Ferramenta para processamento de dados e pipelines de Machine Learning.

## 👨‍💻 Autor

[Fabiano Rocha/Fabiuniz](https://github.com/SeuUsuarioGitHub)

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE)