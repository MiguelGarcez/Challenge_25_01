# Plano de Tarefas - Sistema para Oficinas Mecânicas

Este arquivo monitora o progresso de funcionalidades no projeto. As etapas têm como objetivo evoluir o sistema atual para atender o fluxo de cotações e compras de peças automotivas.

| Id | Título | Status | Prioridade | Dependências |
| -- | ------ | ------ | ---------- | ------------ |
| 1 | Configurar acesso ao banco Oracle | Concluída | Alta | - |
| 2 | Implementar autenticação básica (Spring Security) | Concluída | Alta | 1 |
| 3 | CRUD de Marcas | Concluída | Média | 1 |
| 4 | CRUD de Peças | Concluída | Média | 3 |
| 5 | CRUD de Pedidos | Concluída | Média | 4 |
| 6 | Criar entidade e repositório de Ordem de Serviço (OS) | Concluída | Alta | 1 |
| 7 | Tela para abrir/selecionar OS | Concluída | Alta | 6 |
| 8 | Implementar busca de peças em catálogo | Concluída | Alta | 4 |
| 9 | Montar carrinho de cotação | Pendente | Alta | 6,8 |
|10| Converter cotação em pedido | Pendente | Alta | 9 |

