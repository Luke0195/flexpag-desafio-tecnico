> ### Casos de Succeso


- [X] Recebe uma requisição <b>POST</b> /appointments na rota /api/appoinments.
- [X] Valida os campos obrigatórios.
- [X] Cria uma agendamento com os dados fornecidos.
- [X] Retorna <b> 201 </b>, com os dados do agendamento criado.
- [ ] Retorna <b> 200</b>, com uma lista de agendamentos filtrados por status (paid |  pending)
- [ ] Retorna <b> 204 </b>, quando um agendamento está com status pending.
- [ ] Retorna <b> 200 </b>, quando um agendamento for atualizado com status pending
> ### Excecões

- [X] Retorna <b> 400 </b> se dos dados não forem validos.