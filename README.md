# Projeto: ewave-livraria-pleno


Este projeto tem como objetivo mostrar um pouco da nova stack do Java. O Quakus é uma stack Java nativa do Kubernetes projetada para o OpenJDK HotSpot e GraalVM, criada a partir das melhores bibliotecas e padrões Java de última geração.

No primeiro momento o objetivo era atender aos objetivos obrigatórios do desafio proposto e implementar algumas coisas além. Porém, devido alguns problemas com configurações e a escassez de tempo, estamos entregando a documentação via README + SWAGGER. 

O projeto foi estruturado em camadas observando o padrão DDD, temos a camada de UI utilizando os Resources do Quarkus e a views em HTML. Implementamos também um layout para evitar o alto acoplamento nas views, cada view possui apenas o código que é pertinente a ela.

Na camada de modelo (Domínio), nos preocupamos em não deixar os domínios completamente anêmicos e por isso implementamos os field’s privados sendo acessados apenas pelos getter’s e setter’s. Também implementamos construtores, um padrão para o Hibernate e um passando os parâmetros que geram a classe, facilitando a criação dos nossos modelos de domínio.

Nossos repositórios utilizam o ORM Hibernate com o facilitador nativo do Quarkus, o Panache. As panacheEntities falicitam o desenvolvimento por já conter diversos métodos uteis durante o desenvolvimento. É plenamente possível construir CRUDs usando apenas Hibernate com Panache. Implementamos um método getAll para cada entidade para demonstrar como é simples trabalhar com Panache caso seja necessária a criação de outros métodos de utilização do banco.

Por falar em banco de dados, utilizamos o MySQL com o jdbc nativo do Quarkus. Foi muito simples de configurar e a documentação do Quarkus é bem extensa sobre essas configurações. Nosso maior problema foi no manejo do container do Docker, como meu notebook tem o Windows com SO, foi difícil encontrar ajuda. No começo tudo correu bem, mas assim que precisamos reiniciar o computador o container parou de funcionar o que nos tirou bastante tempo de desenvolvimento. Passados todos os apuros, tudo correu bem. Para executar nossa aplicação será preciso subir um container com MySQL com as configurações conforme especificado no arquivo “application.properties”.

Nosso package de serviço ficou bastante simples, apenas controlando o fluxo da aplicação. Utilizamos a injeção de dependência nativa do Quarkus tanto para injetar os repositórios nas classes de serviços quanto para injetar as classes de serviço na camada de UI.

Sobre a camada de apresentação, pensamos em fazer uma apresentação do projeto na raiz, porém, faltou tempo. Implementamos os 3 CRUDs principais Livro, InstituicaoEnsino e Usuario. Os arquivos HTML ficaram separados em pastas específicas dentro da pasta de templates. Não conseguimos trabalhar bem as validações quanto a campos nulos ou tipo de dado ou ainda formatação dos dados. Ficou bastante coisa a ser feita, mas acredito que o que temos até o momento é bastante coisa e pode demonstrar um pouco do nosso conhecimento sobre desenvolvimento com Java utilizando o que é de mais moderno no mercado.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `ewave-livraria-pleno-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/ewave-livraria-pleno-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/ewave-livraria-pleno-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.
