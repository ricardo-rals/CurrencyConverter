# Currency Converter
![Java Version](https://img.shields.io/badge/Java-17-blue)

Este é um projeto de conversor de moedas em Java, que utiliza a Exchange Rate API para obter taxas de câmbio atualizadas. O programa permite que os usuários convertam valores entre diferentes moedas e mantém um histórico das conversões realizadas.

## Funcionalidades

- Conversão de moedas:
  - Converter de USD para outras moedas suportadas.
  - Converter de outras moedas para USD.
- Adicionar novas moedas à lista de conversão.
- Visualizar histórico de conversões.
- Exibir as taxas de câmbio atualizadas.
- Registros de logs das conversões realizadas, incluindo timestamps.

## Pré-requisitos

- JDK 17 instalado
- Biblioteca Gson para manipulação de JSON (inclusa no projeto)

## Como usar

1. **Configuração inicial:**
   - Execute o programa a partir do método `main` na classe `Main.java`.
   - Informe a moeda base desejada (por exemplo, USD).

2. **Menu Principal:**
   - Escolha uma das opções disponíveis:
     - Converter moedas (de e para a moeda base escolhida).
     - Adicionar uma nova moeda à lista de conversão.
     - Visualizar histórico de conversões.
     - Exibir as taxas de câmbio atuais.
     - Visualizar registros de conversões realizadas.

3. **Logs de Conversão:**
   - A opção de registros de conversão exibe todas as conversões realizadas, mostrando o valor convertido e o timestamp.

## Estrutura do Projeto

- **Main.java:** Contém o método principal que inicia o programa e controla o fluxo de interação com o usuário.
- **ExchangeRateService.java:** Interface para serviço de obtenção de taxas de câmbio.
- **ApiExchangeRateService.java:** Implementação de `ExchangeRateService` que utiliza a Exchange Rate API.
- **ConversionService.java:** Lida com a lógica de conversão entre moedas.
- **UserInterface.java:** Gerencia a interface de usuário por meio do console.
- **ExchangeRates.java:** Representação dos dados de taxas de câmbio obtidos da API.
- **README.md:** Este arquivo, contendo informações sobre o projeto.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para propor melhorias, novas funcionalidades ou correções de bugs através de pull requests.

## Licença

Este projeto está licenciado sob a MIT License - veja o arquivo [LICENSE](LICENSE) para mais detalhes.
