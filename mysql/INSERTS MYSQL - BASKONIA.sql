


INSERT INTO EQUIPO values(1, 'Baskonia', '1946', 'Quejeta', 'Araba', 'TauGres');
select * from EQUIPO;
INSERT INTO JUGADOR values(1, 'Vladimir', 'Perasovich', 'Gonzalez', 2.10 , 90, 'Alero', 'Muy bueno en los triples', 1);
INSERT INTO JUGADOR values(2, 'Jose', 'vichestein', 'Gonzo', 2.15 , 98, 'Pivot', 'Es muy malo', 1);
INSERT INTO JUGADOR values(3, 'Mikel', 'Gonzalez', 'Perez', 1.70 , 70, 'Base', 'Es un matao', 1);
select * from JUGADOR;
INSERT INTO INCIDENCIA values(1, 'Retraso', '1000', 'Llegar tarde');
INSERT INTO INCIDENCIA values(2, 'Insultar', '500', 'Insultar al jefe o a los compañeros');
INSERT INTO INCIDENCIA values(3, 'Falta', '10000', 'No venir al entrenamiento');
select * from INCIDENCIA;
INSERT INTO ENTRENAMIENTO values(1, 'Defensivo', 'Se practica la defensa');
INSERT INTO ENTRENAMIENTO values(2, 'Ofensivo', 'Se practica la ofensa');
INSERT INTO ENTRENAMIENTO values(3, 'Explosivo', 'Para cansar');
select * from ENTRENAMIENTO;
INSERT INTO INCIDENCIA_has_JUGADOR values(1, 1, '2011-03-12');
INSERT INTO INCIDENCIA_has_JUGADOR values(2, 2, '2016-08-12');
INSERT INTO INCIDENCIA_has_JUGADOR values(1, 3, '2017-03-13');
INSERT INTO INCIDENCIA_has_JUGADOR values(3, 2, '2015-05-14');
INSERT INTO INCIDENCIA_has_JUGADOR values(1, 1, '2014-04-18');
delete FROM INCIDENCIA_has_JUGADOR WHERE FECHA='0000-00-00';
select * from INCIDENCIA_has_JUGADOR;