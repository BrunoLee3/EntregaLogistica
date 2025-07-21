INSERT INTO tipo_caminhao (tipo)
VALUES
('Truck'),
('Carreta'),
('Van');

INSERT INTO tipo_paletizacao (tipo)
VALUES
('Paletizado'),
('Não Paletizado');

INSERT INTO faixa_horario (hora_inicio, hora_fim, descricao)
VALUES
('08:00:00', '10:00:00', '08:00 - 10:00'),
('10:00:00', '12:00:00', '10:00 - 12:00'),
('12:00:00', '14:00:00', '12:00 - 14:00'),
('14:00:00', '16:00:00', '14:00 - 16:00');

INSERT INTO pedido (qtd_paletes)
VALUES
(25),
(4),
(479),
(12),
(6),
(30),
(10),
(111),
(56),
(67),
(23),
(45);

INSERT INTO agendamento (data, id_faixa_horario, id_pedido, fornecedor, email_fornecedor, id_tipo_caminhao, id_tipo_paletizacao, obs)
VALUES
('2025-07-22', 1, 1, 'Fornecedor A', 'fornecedorA@email.com', 1, 1, NULL),
('2025-07-23', 4, 2, 'Fornecedor B', 'fornecedorB@email.com', 2, 2, NULL),
('2025-07-23', 2, 3, 'Fornecedor C', 'fornecedorC@email.com', 1, 1, NULL),
('2025-07-22', 3, 4, 'Fornecedor D', 'fornecedorD@email.com', 3, 2, NULL),
('2025-07-21', 1, 5, 'Fornecedor E', 'fornecedorE@email.com', 1, 2, NULL),
('2025-07-23', 1, 6, 'Fornecedor F', 'fornecedorF@email.com', 2, 1, 'Marca própria');


