# Simulação de Restaurante com Threads
Este projeto é uma simulação de um restaurante onde garçons atendem clientes de forma concorrente utilizando Java 17.

## Descrição
O sistema possui:
- Clientes (Customer): Representados por um record, cada cliente possui um nome único.
- Garçons (Waiter): Implementados como <b>threads,</b> atendem os clientes simulando um tempo aleatório de atendimento.
- Restaurante (Restaurant): Gerencia a fila de clientes e distribui os atendimentos para os garçons usando um <b>ExecutorService.</b>
- Classe principal (Main): Responsável por iniciar a simulação.

## Mecanismo de Encerramento
A simulação possui um mecanismo para encerrar a execução de forma controlada:
<b>Um sinalizador volatile boolean</b> running para indicar quando o restaurante deve parar.
O método stop() que finaliza os garçons e o ExecutorService corretamente.
Os garçons verificam se o restaurante está ativo antes de continuar atendendo.
Após de um tempo estabelecido (na classe Main) o restaurante encerra automaticamente.

## Tecnologias Utilizadas
- Java 17
- ExecutorService para gerenciamento eficiente de threads
- synchronized e wait/notify para controle de concorrência na fila de clientes
