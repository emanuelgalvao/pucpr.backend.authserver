# AuthServer

## O primeiro serviço

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
