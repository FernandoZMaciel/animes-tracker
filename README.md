# AnimesTracker

AnimesTracker é uma aplicação que permite aos usuários registrar e acompanhar os animes que assistiram. Utilizando uma API, os usuários podem gerenciar suas listas de animes com diferentes status, como "plantowatch", "completed", "dropped" e "watching". Além disso, a aplicação recomenda novos animes com base nas preferências do usuário.

## Funcionalidades

- Registro de usuários armazenados em um banco de dados MongoDB.
- Integração com a API do Anilist para obter informações sobre animes.
- Permite que os usuários registrem animes assistidos com os seguintes status:
  - Plantowatch
  - Completed
  - Dropped
  - Watching
- Recomendação de animes com base nos dados do usuário, levando em consideração:
  - Gêneros mais assistidos
  - Tags mais assistidas

## Tecnologias Usadas

- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **Spring Boot**: Framework para criar a API de forma rápida e eficiente.
- **MongoDB**: Banco de dados NoSQL utilizado para armazenar informações dos usuários e seus registros de animes.
- **OkHttp**: Biblioteca para realizar chamadas HTTP e facilitar a comunicação com a API do Anilist.

## Informações do Autor

- **Nome**: Fernando Zimmermann Maciel
- **Contato**: https://www.linkedin.com/in/fernando-zimmermann-maciel-917a88198/
- **GitHub**: https://github.com/FernandoZMaciel
## Como Usar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/animestracker.git
