# _Gaming "Hello, World!"_

Esse repositório contém o meu _"Hello, World!"_ do desenvolvimento de jogos,
sendo um jogo com o propósito de aprender os conceitos de _game dev_ e de
_pixel art_ para criação de _sprites_. Decidi por utilizar as seguintes 
ferramentas: **_Java_** para lidar com toda a lógica, **_LibreSprite_** para criação
de _sprites_, e **_IntelliJ_** para servir de ambiente de desenvolvimento e gerenciamento de pacotes.

### Execução

Pré requisitos: **JDK 21** ou superior. Este projeto foi compilado utilizando o JDK 21, sendo este necessário
para a sua execução.
- Fazer o download do JAR mais atual na seção de **_releases_**.
- Executar o arquivo no terminal:
  ```
  java -jar caminho\para\o\arquivo\Gaming_HelloWorld.jar
  ```

## **_Purranormal Maze_**

**_Purranormal Maze_** é um jogo de exploração de labirintos feitos através
de geração procedural de maneira aleatória, derrotando inimigos e coletando
objetos distribuídos no mapa. 

A protagonista do jogo é a gata Meg, uma felina que consegue invocar
espíritos elementais de fogo, gelo e eletricidade, que gosta de
colecionar itens para levar para o seu dono.

O objetivo do jogo é encontrar todos os baús no menor tempo possível, 
derrotando o máximo de inimigos e coletando o máximo de itens.

### Controles
**W, A, S, D** - Movimentação

**P** - Pause

**M** - Miar

**J** - Arranhar

**K** - Magia de fogo (A ser implementado)

**U** - Magia de gelo (A ser implementado)

**I** - Magia de eletricidade (A ser implementado)

### Personagens e inimigos

| <img src="resources\previews\cat.gif"/>                  | <img src="resources\previews\slime.gif"/> | <img src="resources\previews\mimic.gif"/> |
|----------------------------------------------------------|-------------------------------------------|-------------------------------------------|
| <div align=center>Meg</div>                              | <div align=center>Slime</div>             | <div align=center>Mimic</div>             |
| Personagem principal. Pode<br/> invocar seres elementais | Uma meleca                                | Um baú falso                              |



## Algoritmo de geração de labirintos

### _Binary Tree Algorhitm_ 

Este algoritmo é bem simples. Dentro de uma matriz, percorre-se
cada uma das "casas" e, aleatoriamente, conecta com a casa norte
ou com a casa oeste, abrindo um caminho entre elas. Aonde não há
caminhos abertos, é como se fosse uma parede

### _Hunt and Kill_

(A ser implementado)