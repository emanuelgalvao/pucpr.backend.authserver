# AuthServer

## Aula 1 - O primeiro serviço

### Instalar SpringBoot

- Intelijj
- Sprint Initializr - authserver o nome do projeto
    - Gradle
    - Kotlin
    - Jar
    - Dependencias
        - Spring Web
        - Validation 
- Adicionar spring doc
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.4.0")
- application.properties
    server.servlet.context-path=/api
    springdoc.swagger-ui.use-root-path=true

### Criar entidade Usuário

- MVC
- Organização de pastas por entidades
- Criar como class e não data class
- Atributos
    - Id - Long?
    - Nome
    - Email
    - Senha

### Permitir inserção de um novo usuário

- POST /users
- Retorna um 201 Created usando ResponseENtity
- UserController - Rotas - @RestController
    - Recebe o service no construtor
    - RequestMapping("/users")
    - Método insert - recebe UserRequest (sem id e atributos nullable) e retorna UserResponse - @PostMapping e @RequestBody e @Valid após RequestBody
    - UserRequest
        - Validation @field:Email e @field:NotBlank, name pode ser null
- UserService - Lógica e chama Repository - @Service
    - Recebe o repository no construtor
    - Apenas chama o repository
- UserRepository - Gerencia os Dados - @Component
    - Método save - recebe User e retorna User
    - Salva os dados em um Map
    - Id controlado por um Long lastId

### Listar usuários cadastrados

- GET /users
- Retorna uma List<UserReponse>
- No mesmo estilo do endpoint de POST /users
- Método findAll()

### Obter usuário por ID

- GET /users/{id}
- Recebe o id como @PathVariable
- Retorna um UserResponse
- Se não existir retorna um 404
- Método findByIdOrNull no service e repository
- Método findById no controller
    - Se não encontrar retorna um ResponseEntity.notFound().build()
    - Se encontrar retorna um ResponseEntity.ok

### Excluir usuários

- DELETE /users/{id}
- Retorna 200 ou um 404

### Ordenação dos usuários

- GET /users?sortDir=ASC
- Não diferencia letras maiusculas ou minusculas
- Se omitido a ordenação vai ser ascendente
- Outros valores de query deve retornar 404 Bad Request

## Aula 2 - Persistência com JPA

### Configurar o JPA e o banco de dados

- Adicionar as bibliotecas do JPA e H2DB ao Gradle
    - implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    - runtimeOnly("com.h2database:h2")
- Adicionar rotas de conexão no application.properties
  spring.datasource.url=jdbc:h2:mem:db
  spring.datasource.username=sa
  spring.datasource.password=sa
  spring.jpa.show-sql=true
  spring.h2.console.enabled=true
- Configurar os plugins
    - build.gradle
      allOpen {
      annotation("jakarta.persistance.Entity")
      }
      plugin kotlin("plugin.jpa") version mesma spring
    - Entidades devem ter o @Entity
    - Não usar data class para entidades
    - Entidade precisa ter um construtor vazio

### Persistir a Entidade User

- @Table altera o nome da tabela
- Marcar o Id como @Id e @GeneratedValue
- Marcar outros campos no @NotNull
- Marcar email com @Column unique
- /h2-console abre o console do h2 da api
- Mudar UserRepository para uma interface UserRepository que herda do JpaRepository<User, Long> e tem a anotação @Repository
- Podemos passar para o findAll um Sort.by("name").ascending() e .descending()

### POST /roles

{
"name": "USER",
"description": "Default user"
}

- Nome deve começar por uma letra. Deve conter somente letras maiusculas e números @Pattern(regexp = "^[A-Z][A-Z0-9]+$")
- Descrição não pode estar em branco
- Nome não pode ser duplicado

### GET /roles

- Retornar a lista de papeis ordenados pelo campo name

### PUT /users/{id}/roles/{name}

- Se o usuário não existir retornar NotFound
    - Por hora está disparando um throw, futuramente vamos mudar
- Se o papel não existir, retornar Bad Request
    - Papel não diferencia maiusculas e minusculas
- Se o usuário já possuir o papel, retornar NoContent
- Se um usuário e papel válidos forem inseridos o papel é adicionado ao usuário e OK é retornado sem corpo de resposta
- Role é @ManyToMany, usuário pode ter mais de um papel
  @JoinTable(
  name="UserRole",
  joinColumns = [JoinColumn(name="idUser")],
  inverseJoinColumns = [JoinColumn(name="idRole")]
  )