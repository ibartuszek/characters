# Characters API:
The API serves list of characters or information about a specified character by id.

### Sample Usage
Via Postman simple calls or with the Swagger interface: 
<a href=http://localhost:8080/swagger-ui.html#/>localhost:8080/swagger-ui.html</a>

### Dependencies:

 - Spring-boot
 - google:gson
 - springfox-swagger
 - co.aurasphere:jyandex.

### Build:
 - `./mvnw clean package` at the main folder of the project 
 
 Set system environment variables:
 - `MARVEL_PRIVATE_KEY`
 - `MARVEL_PUBLIC_KEY`
 - `YANDEX_KEY`
 
 <sub>The first two is available registered users of <a href="developer.marvel.com">developer.marvel.com</a></sub>
 <sub>You will need a <a href="https://tech.yandex.com/keys/get/?service=trnsl">free API key</a> in order to use the Yandex API at the third variable.</sub>

## Run:
 
 - `./mvnw spring-boot:run` at the main folder of the project to start application.

## Useful links

- <a href="https://github.com/aurasphere/jyandex">Yandex API by Donato Rimenti for translation</a>


## License
The project is released under the MIT license, which lets you reuse the code for any purpose you want (even commercial) with the only requirement being copying this project license on your project.
