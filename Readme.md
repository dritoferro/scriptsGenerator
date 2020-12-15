# Scripts Generator

O propósito desta utility é facilitar a vida para algumas tasks que aparecem no dia a dia como por exemplo,
 a geração de URLs para serem executadas via script ou geração de bulk inserts de SQL.

Atualmente a aplicação está na V1 e portanto, é bem simples e limitada, mas já atende as demandas atuais.

**Está aberto a sugestões de melhorias via PRs.**

## How to use

Basta executar a função `main` dentro do arquivo **Generator.kt**

Os arquivos de entrada devem ser na extensão ".txt" e devem ficar na pasta `resources/input`.

Os inputs necessários serão:

- O nome de um ou mais arquivos que deseja utilizar para compor o arquivo final, sendo que cada arquivo deve conter apenas o valor de uma chave,
 por exemplo, um arquivo contém uma lista de ids de produtos. Basta informar o nome do arquivo sem a extensão.
 A separação das linhas de cada arquivo será feita pela quebra de linha ("\n"). Quando informar todos os arquivos necessários, pressione **0(zero)**.

- O segundo input será as palavras que serão substituídas pelos valores de cada arquivo, separadas por vírgula e sem espaço, por exemplo: `product_id,product_name`.

- O terceiro input será a URL, query SQL ou outra coisa que deseja montar, já tendo os headers que foram informados no passo anterior, por exemplo: 
    
    - curl --location --request POST 'https://www.google.com.br/products/$id/details'
    
    - INSERT INTO tablename(product_id, product_name) VALUES('$product_id', '$product_name');
    
Após isso, se tudo estiver correto, o seu arquivo será gerado com sucesso na pasta `resources/output`.
