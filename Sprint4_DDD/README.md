# MechanicApp

Sistema exemplo em **Java 17 / Spring Boot 3** que aplica _Domain‑Driven Design_ (DDD) a um CRUD completo de **Marcas**, **Veículos** e **Pedidos**, com autenticação via Spring Security, validação de dados (Bean‑Validation) e persistência em **Oracle Database**.

---

## ✨ Funcionalidades

| Módulo | Ações | Validações principais |
| ------ | ----- | --------------------- |
| **Marcas** | Listar, criar, editar, excluir | Nome obrigatório e único |
| **Veículos** | Listar, criar, editar, excluir | Marca obrigatória, modelo e ano obrigatórios |
| **Pedidos** | Listar, criar, editar, excluir | Veículo obrigatório, quantidade mínima = 1 |

Recursos adicionais:

* Menu principal com navegação rápida  
* Botão **Voltar** em todas as telas  
* Layout simples em CSS (responsivo)  
* Mensagens de erro inline, evitando *Whitelabel Error Page*  

---

## 🏗️ Arquitetura

```
com.ancora.mechanicapp
├── domain        (Entidades + Repositórios Spring Data)
├── application   (Serviços / Use‑cases)
├── presentation  (Controllers MVC + Thymeleaf)
└── config        (Spring Security, Converters, etc.)
```

---

## 🖥️ Pré‑requisitos

| Ferramenta        | Versão recomendada |
| ----------------- | ------------------ |
| Java JDK          | **17** ou superior |
| Maven             | 3.9.x              |
| Oracle Database   | 12c+ (testado no XE **21c**) |
| Conta Oracle      | Usuário com permissão de **CREATE TABLE** |

> O driver **ojdbc11** já está incluído como dependência *runtime*.

---

## ⚙️ Configuração

1. **Clone** o repositório ou baixe o zip.  
2. Edite `src/main/resources/application.properties` com suas credenciais:

   ```properties
   spring.datasource.url=jdbc:oracle:thin:@<HOST>:<PORT>:<SID>
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA
   ```

   Exemplo usado em aula:  
   `jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl`

3. Ajuste, se necessário, a política de geração de schema:

   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```

   - `update` cria/atualiza as tabelas automaticamente.  
   - `none` em produção.

---

### ▶️ Execução

Aplicação disponível em **http://localhost:8080**

| Usuário | Senha |
| ------- | ----- |
| `admin` | `password` |

> Altere em `SecurityConfig.java` conforme necessidade.

### Endpoints

| URL        | Descrição          |
| ---------- | ------------------ |
| `/home`    | Menu principal     |
| `/brands`  | CRUD de Marcas     |
| `/vehicles`| CRUD de Veículos   |
| `/orders`  | CRUD de Pedidos    |
| `/login`   | Autenticação       |

---

## 🗄️ Modelo de Dados

```
BRAND   (id PK, name)~~~~
VEHICLE (id PK, brand_id FK, model, year)
ORDERS  (id PK, vehicle_id FK, quantity, created_at)
```

* Chave primária `IDENTITY`.  
* FKs garantem integridade referencial.

---
~~~~
## 📄 Licença

Projeto acadêmico – uso livre para fins educacionais.
