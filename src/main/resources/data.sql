-- Vendedores
INSERT INTO vendedor (nombre) VALUES ('Perico P');
INSERT INTO vendedor (nombre) VALUES ('Zoila Baca');
INSERT INTO vendedor (nombre) VALUES ('Aquiles C');
INSERT INTO vendedor (nombre) VALUES ('Johny Melas');

-- Reglas (monto minimo -> porcentaje)
INSERT INTO regla (monto_minimo, porcentaje) VALUES (600.00, 6.00);
INSERT INTO regla (monto_minimo, porcentaje) VALUES (500.00, 8.00);
INSERT INTO regla (monto_minimo, porcentaje) VALUES (800.00, 10.00);
INSERT INTO regla (monto_minimo, porcentaje) VALUES (1000.00, 15.00);

-- Ventas
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (1, '2025-05-21', 400.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (2, '2025-05-29', 600.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (3, '2025-06-03', 200.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (4, '2025-06-09', 300.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (1, '2025-06-11', 500.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (2, '2025-06-15', 300.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (3, '2025-06-18', 900.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (4, '2025-06-21', 700.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (1, '2025-06-25', 250.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (2, '2025-07-02', 1200.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (3, '2025-07-08', 150.00);
INSERT INTO venta (vendedor_id, fecha, monto) VALUES (4, '2025-07-14', 450.00);
