# Rest Assured API Test — Dog API

> Automação de testes de API para a **Dog API (dog.ceo)** usando **Java 17 + Maven + JUnit 5 + Rest-Assured + Allure**.

**Relatórios (GitHub Pages):** https://alexander2005rj.github.io/rest-assured-api-test/

---

## 📦 Informações do Projeto

- **Linguagem**: Java 17+
- **Frameworks**: JUnit 5, Rest Assured, Java Faker
- **Build**: Maven
- **Relatórios:** Allure Report
- **CI/CD:** GitHub Actions (gera artefatos e publica o Allure em `gh-pages`)
- **API alvo:** [Dog API — dog.ceo](https://dog.ceo/dog-api/)

### Estrutura do projeto

```
├── src
│   ├── test
│   │   ├── java
│   │   │   ├── config/ (configuração base de testes e interface de endpoints)
│   │   │   ├── utils/ (utilitários para facilitar o reuso nos testes)
│   │   │   └── specs/ (testes organizados por recurso)
│   │   └── resources
│   │       ├── schemas/ (arquivos *.json para validação de contrato)
│   │       └── allure.properties (configuração do target para resultados do Allure)
├── pom.xml
├── .gitignore
└── README.md
```

---

## ▶️ Como executar

### 1) Pré-requisitos
- **Java 17+** instalado e no `PATH`
- **Maven 3.8+**
- (Opcional para visualizar relatório local) **Allure CLI**  
  Instale via **npm**:
  ```bash
  npm i -g allure-commandline

### 2) Clonar o projeto e testar - Local

```bash
# Clonar o projeto e acessar o projeto
git clone https://github.com/alexander2005rj/rest-assured-api-test.git
cd rest-assured-api-test

# limpar cache de testes anteriores
mvn clean

# rodar todos os testes
mvn -q clean test

# rodar um pacote de testes específico (ex.: lista de raças)
mvn -q -Dtest=*BreedListTest test

# gerar e abrir relatório Allure
mvn allure:report && mvn allure:serve

# rodar todos os testes, gerar e abrir relatório Allure
mvn -q clean test && mvn allure:report && mvn allure:serve
```


### 3) Testar via **GitHub Actions**

> A execução está automática. Sempre que algo for enviado para main, a pipeline será executada. Depois disso, os resultados do `Allure Reports` ficam disponíveis na **GitHub Pages**: `https://alexander2005rj.github.io/rest-assured-api-test/`.



---

## 🧪 Estratégia & Plano de Testes

### Escopo

Cobrir os endpoints:
* `GET /api/breeds/list/all` – Listar todas as raças
* `GET /api/breed/{breed}/images` – Imagens por raça
* `GET /api/breeds/image/random` – Imagem aleatória

### Abordagem

* **Funcional Happy Path**: validar status code, headers essenciais, e payload mínimo esperado.
* **Contrato (Schema Validation)**: JSON Schema por endpoint (ex.: `schemas/get-all-breeds-schema.json`) com validação via Rest Assured + `json-schema-validator`.

## 🐞 Bugs / Inconsistências observadas

> Não foram observados bugs funcionais confirmados na documentação oficial; abaixo estão achados/práticas que podem causar falhas no cliente se não tratados.

`GET /api/breeds/list/all`
* **N/A** — rota simples e estável; sem parâmetros.

`GET /api/breed/{breed}/images`
* **Trailing slash:** o caminho canônico é **sem** barra no final (`.../images`). Alguns clientes podem receber **404** se chamarem `.../images/` (varia por reverse proxy/roteamento).
* **Volume de dados:** retorna todas as imagens da raça (sem paginação), podendo gerar payloads grandes e latência maior. 

`GET /api/breeds/image/random`
* **Limite de múltiplas imagens:** `.../random/{n}` com **máximo 50**; clientes que pedirem além disso devem tratar o retorno adequadamente.

---

## 💡 Melhorias sugeridas (por rota)

`GET /api/breeds/list/all`
* **Metadados**: incluir **count**, **last_updated** e ordenação garantida ajudaria consumidores e testes.
* **Caching**: cabeçalhos **ETag**/**Cache-Control** para reduzir chamadas repetidas.

`GET /api/breed/{breed}/images`
* **Paginação e limite:** suportar `?limit=/?page=` para reduzir payload e tempo de resposta.
* **Trailing slash:** aceitar `.../images/` como equivalente a `.../images` para evitar **404** por variação de URL.

`GET /api/breeds/image/random`
* **Erro padronizado para** `n > 50`: documentar claramente o comportamento (truncar para 50, 4xx, etc.) e padronizar payload de erro.
* **Campos extras:** opcionalmente incluir a **raça** no retorno junto da URL para enriquecer o uso.


---

## 🔧 Notas de Implementação (Automação)

* **Config**: usa `RequestSpecification` comum (baseURI, content‑type, logging condicional), `ResponseSpecification` por endpoint (status esperado, tempo máximo, headers);
* **Specs**: são os `Tests` criados para cada `endpoint` testado.
* **Utils**: criados para `reuso` durante os testes contidos nas `Specs`.
* **Resources/Schemas**: para validação de `JSON Schema` das respostas dos `endpoints`.
* **Resources/allure.properties**: destinado como propriedade para definir o `results directory` do `Allure Reports`.