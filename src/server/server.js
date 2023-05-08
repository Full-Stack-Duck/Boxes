const express = require('express');
const cors = require('cors');

const app = express();
const port = 3333; // Escolha uma porta para o seu servidor

app.use(cors({
    origin: '*'
  }));

// Crie uma rota para acessar o arquivo .json
app.get('/resources', (req, res) => {
  res.sendFile(__dirname + '/data.json');
});

// Inicie o servidor
app.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`);
});
