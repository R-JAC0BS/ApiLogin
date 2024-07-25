# Login API JWT

## Como configurar



![](../apiLogin/img/img.png)

#### 1º - Adicione o link do deploy do seu banco de dados em spring.datasource.url
#### 2º - Coloque seu username/passowrd do banco de dados em spring.datasource.username/passowrd
###### Observação: os comandos abaixo  geram automaticamente o banco de dados da API caso deseje utilizar seu propio banco de dados deve remover esse comandos e linkar direto ao criado manualmente


## Como utilizar

### ROTAS:
###### Rota de Cadastro
####  [seulink]/auth/signup
###### Rota de Login
####  [seulink]/auth/signin
###### Rota home (Essa rota para acessar necessita do token gerado durante o login para continuar)
####  [seulink]/auth/ws/home

## Realizar teste

### POSTMAN (metodo POST) /SIGNUP
###### CPF E EMAIL DEVEM SER VALIDOS

![](../apiLogin/img/img_1.png)

### POSTMAN (metodo POST) /SIGNIN
![](../apiLogin/img/img_2.png)
###### TOKEN RETORNADO
![](../apiLogin/img/img_3.png)
### POSTMAN (metodo GET) /HOME
![](../apiLogin/img/img_4.png)
###### METODO DEVE CONTER O TOKEN GERADO INSERIDO MANUALMENTE NO POSTMAN EM AUTHORIZATION






