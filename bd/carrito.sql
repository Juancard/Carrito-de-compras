--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: actualizar_producto(integer, character varying, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION actualizar_producto(INOUT _codigo integer, INOUT _descripcion character varying, INOUT _precio numeric) RETURNS record
    LANGUAGE plpgsql
    AS $$
BEGIN
	UPDATE 
	producto
	SET 
	descripcion = _descripcion,
	precio = _precio
	WHERE codigo_producto = _codigo;
END;$$;


ALTER FUNCTION public.actualizar_producto(INOUT _codigo integer, INOUT _descripcion character varying, INOUT _precio numeric) OWNER TO postgres;

--
-- Name: insertar_cliente(character varying); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insertar_cliente(INOUT _nombre character varying, OUT _codigo integer) RETURNS record
    LANGUAGE plpgsql
    AS $$
BEGIN
	_codigo = nextval('cliente_codigo_cliente_seq'::regclass);
	INSERT INTO cliente 
	VALUES (
	_codigo,
	_nombre
	);
END;$$;


ALTER FUNCTION public.insertar_cliente(INOUT _nombre character varying, OUT _codigo integer) OWNER TO postgres;

--
-- Name: insertar_detalleventa(integer, integer, integer, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insertar_detalleventa(INOUT _codigo_venta integer, INOUT _codigo_producto integer, INOUT _cantidad integer, INOUT _descuento numeric) RETURNS record
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO detalleventa 
	VALUES (
	_codigo_venta,
	_codigo_producto,
	_cantidad,
	_descuento
	);
END;$$;


ALTER FUNCTION public.insertar_detalleventa(INOUT _codigo_venta integer, INOUT _codigo_producto integer, INOUT _cantidad integer, INOUT _descuento numeric) OWNER TO postgres;

--
-- Name: insertar_detalleventa(integer, integer, integer, numeric, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insertar_detalleventa(INOUT _codigo_venta integer, INOUT _codigo_producto integer, INOUT _cantidad integer, INOUT _descuento numeric, INOUT _precio_unitario numeric) RETURNS record
    LANGUAGE plpgsql
    AS $$BEGIN
	INSERT INTO detalleventa 
	VALUES (
	_codigo_venta,
	_codigo_producto,
	_cantidad,
	_descuento,
	_precio_unitario
	);
END;$$;


ALTER FUNCTION public.insertar_detalleventa(INOUT _codigo_venta integer, INOUT _codigo_producto integer, INOUT _cantidad integer, INOUT _descuento numeric, INOUT _precio_unitario numeric) OWNER TO postgres;

--
-- Name: insertar_producto(character varying, numeric); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insertar_producto(INOUT _descripcion character varying, INOUT _precio numeric, OUT _codigo integer) RETURNS record
    LANGUAGE plpgsql
    AS $$BEGIN
	_codigo = nextval('producto_codigo_producto_seq'::regclass);
	INSERT INTO producto 
	VALUES (
	_codigo,
	_descripcion,
	_precio
	);
END;$$;


ALTER FUNCTION public.insertar_producto(INOUT _descripcion character varying, INOUT _precio numeric, OUT _codigo integer) OWNER TO postgres;

--
-- Name: insertar_venta(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION insertar_venta(INOUT _codigo_cliente integer, OUT _fecha date, OUT _codigo_venta integer) RETURNS record
    LANGUAGE plpgsql
    AS $$BEGIN
	_fecha = CURRENT_DATE;
	_codigo_venta = nextval('venta_codigo_venta_seq');
	INSERT INTO venta 
	VALUES (
	_codigo_venta,
	_codigo_cliente,
	_fecha
	);
END;$$;


ALTER FUNCTION public.insertar_venta(INOUT _codigo_cliente integer, OUT _fecha date, OUT _codigo_venta integer) OWNER TO postgres;

--
-- Name: producto_codigo_producto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE producto_codigo_producto_seq
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.producto_codigo_producto_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE producto (
    codigo_producto integer DEFAULT nextval('producto_codigo_producto_seq'::regclass) NOT NULL,
    descripcion character varying(50) NOT NULL,
    precio numeric(10,2) NOT NULL
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- Name: obtener_producto(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION obtener_producto(_codigo integer) RETURNS SETOF producto
    LANGUAGE plpgsql
    AS $$
DECLARE
	registro producto%ROWTYPE;
BEGIN
	SELECT *
	INTO registro
	FROM producto
	WHERE codigo_producto = _codigo;
	return next registro;
	
END;$$;


ALTER FUNCTION public.obtener_producto(_codigo integer) OWNER TO postgres;

--
-- Name: cliente_codigo_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE cliente_codigo_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_codigo_cliente_seq OWNER TO postgres;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE cliente (
    codigo_cliente integer DEFAULT nextval('cliente_codigo_cliente_seq'::regclass) NOT NULL,
    nombre character varying(50) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: detalleventa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE detalleventa (
    codigo_venta integer NOT NULL,
    codigo_producto integer NOT NULL,
    cantidad integer NOT NULL,
    descuento numeric(10,2),
    precio_unitario numeric(10,2) NOT NULL
);


ALTER TABLE public.detalleventa OWNER TO postgres;

--
-- Name: venta_codigo_venta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE venta_codigo_venta_seq
    START WITH 2
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venta_codigo_venta_seq OWNER TO postgres;

--
-- Name: venta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE venta (
    codigo_venta integer DEFAULT nextval('venta_codigo_venta_seq'::regclass) NOT NULL,
    codigo_cliente integer NOT NULL,
    fecha date NOT NULL
);


ALTER TABLE public.venta OWNER TO postgres;

--
-- Name: historico_ventas2; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW historico_ventas2 AS
    SELECT v.codigo_venta, c.codigo_cliente, c.nombre, v.fecha, d.codigo_producto, p.descripcion, p.precio, d.cantidad, d.descuento, (p.precio * (d.cantidad)::numeric) AS parcial, ((p.precio * (d.cantidad)::numeric) - d.descuento) AS subtotal, (SELECT sum((((dt.cantidad)::numeric * pt.precio) - dt.descuento)) AS totalpagar FROM (detalleventa dt JOIN producto pt ON ((dt.codigo_producto = pt.codigo_producto))) WHERE (dt.codigo_venta = v.codigo_venta)) AS totalpagar FROM (((venta v JOIN detalleventa d ON ((v.codigo_venta = d.codigo_venta))) JOIN producto p ON ((d.codigo_producto = p.codigo_producto))) JOIN cliente c ON ((c.codigo_cliente = v.codigo_cliente))) ORDER BY v.codigo_venta, p.descripcion;


ALTER TABLE public.historico_ventas2 OWNER TO postgres;

--
-- Name: obtener_venta(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION obtener_venta(_codigo integer) RETURNS SETOF historico_ventas2
    LANGUAGE plpgsql
    AS $$
DECLARE
	registro historico_ventas2%ROWTYPE;
BEGIN
	FOR registro IN
		SELECT * FROM historico_ventas2 h
		WHERE h.codigo_venta = _codigo
		ORDER BY h.codigo_venta
	LOOP
		RETURN NEXT registro;
	END LOOP;
	
END;$$;


ALTER FUNCTION public.obtener_venta(_codigo integer) OWNER TO postgres;

--
-- Name: historico_ventas; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW historico_ventas AS
    SELECT v.codigo_venta, c.codigo_cliente, c.nombre, v.fecha, d.codigo_producto, p.descripcion, d.precio_unitario, d.cantidad, d.descuento, (d.precio_unitario * (d.cantidad)::numeric) AS parcial, ((d.precio_unitario * (d.cantidad)::numeric) - d.descuento) AS subtotal, (SELECT sum((((dt.cantidad)::numeric * dt.precio_unitario) - dt.descuento)) AS totalpagar FROM detalleventa dt WHERE (dt.codigo_venta = v.codigo_venta)) AS totalpagar FROM (((venta v JOIN detalleventa d ON ((v.codigo_venta = d.codigo_venta))) JOIN producto p ON ((d.codigo_producto = p.codigo_producto))) JOIN cliente c ON ((c.codigo_cliente = v.codigo_cliente))) ORDER BY v.codigo_venta, p.descripcion;


ALTER TABLE public.historico_ventas OWNER TO postgres;

--
-- Name: obtener_clientes; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW obtener_clientes AS
    SELECT cliente.codigo_cliente, cliente.nombre FROM cliente ORDER BY cliente.codigo_cliente;


ALTER TABLE public.obtener_clientes OWNER TO postgres;

--
-- Name: obtener_detalle_ventas; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW obtener_detalle_ventas AS
    SELECT detalleventa.codigo_venta, detalleventa.codigo_producto, detalleventa.cantidad, detalleventa.descuento, detalleventa.precio_unitario FROM detalleventa ORDER BY detalleventa.codigo_venta;


ALTER TABLE public.obtener_detalle_ventas OWNER TO postgres;

--
-- Name: obtener_productos; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW obtener_productos AS
    SELECT producto.codigo_producto, producto.descripcion, producto.precio FROM producto ORDER BY producto.codigo_producto;


ALTER TABLE public.obtener_productos OWNER TO postgres;

--
-- Name: obtener_ventas; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW obtener_ventas AS
    SELECT venta.codigo_venta, venta.codigo_cliente, venta.fecha FROM venta ORDER BY venta.codigo_venta;


ALTER TABLE public.obtener_ventas OWNER TO postgres;

--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY cliente (codigo_cliente, nombre) FROM stdin;
1	Jorge Perez
2	Carlos Ruiz
3	Adolfo Castro
5	Fernando Bores
4	Raul Alvarez
6	Mirta Tolossa
7	Mariana Valles
8	Alfredo Carrasco
9	Nicanor Ruiz
10	Laura Diaz
11	Pablo Ballester
12	Marcela Almenabar
13	Micaela Garcia
14	Walter Ponzio
15	Macarena Villaruiz
16	Carlos Pedroza
17	Alicia Chercio
18	Wilfredo Vargas Distra
19	Gregorio Samsa
20	Marizza Armero
21	Amanda Nara
22	Álvaro Artisberea
23	Claudia Moreira
24	Brian Moreno
25	Mariano Palma
26	Alan Nicosia
27	Verónica Raldes
\.


--
-- Name: cliente_codigo_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('cliente_codigo_cliente_seq', 27, true);


--
-- Data for Name: detalleventa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY detalleventa (codigo_venta, codigo_producto, cantidad, descuento, precio_unitario) FROM stdin;
2	4	1	2.00	89.00
14	6	4	50.00	100.00
6	6	1	2.00	100.00
7	9	1	2.00	78.00
5	9	2	5.00	78.00
12	10	1	0.00	70.00
19	11	1	50.00	132.50
19	12	1	30.00	49.90
12	14	1	0.00	439.90
8	15	2	10.00	89.90
10	18	2	10.50	125.00
8	18	1	0.00	125.00
8	19	3	30.00	55.50
18	20	1	0.00	70.99
8	22	1	20.00	220.99
8	26	1	15.00	125.00
18	31	1	100.00	360.00
18	5	1	0.00	65.00
6	7	1	2.00	150.00
18	32	1	0.00	124.90
16	34	1	50.00	319.90
15	35	1	0.00	598.90
18	36	1	20.00	79.00
19	21	1	0.00	82.00
8	21	1	0.00	82.00
11	8	1	0.00	87.89
7	8	1	2.00	87.89
6	8	1	2.00	87.89
4	2	2	5.00	355.00
1	2	2	5.00	355.00
15	43	1	20.00	225.00
18	44	1	0.00	110.00
13	44	1	0.00	110.00
9	3	1	5.00	470.00
8	3	1	10.00	470.00
4	3	1	2.00	470.00
1	3	1	1.00	470.00
19	45	3	100.00	349.90
13	45	2	5.00	349.90
10	1	1	0.70	479.90
3	1	1	2.00	479.90
2	1	1	2.00	479.90
1	1	10	10.00	479.90
17	39	5	100.00	125.50
15	33	1	0.00	1015.99
20	3	1	10.00	520.00
21	46	2	0.00	479.90
22	43	2	0.00	225.00
22	12	2	50.00	89.90
22	21	1	0.00	92.00
23	11	1	0.00	132.50
\.


--
-- Data for Name: producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY producto (codigo_producto, descripcion, precio) FROM stdin;
12	La iliada	89.90
21	Mi planta naranja lima	92.00
11	David Copperfield	132.50
4	El extranjero	89.00
6	El idiota	100.00
9	Veinte mil leguas de viaje submarino	78.00
10	El hombre invisible	70.00
13	Muerte en Venecia	99.99
14	Los hermanos Karamazov	439.90
15	El principito	89.90
16	Los tres mosqueteros	125.00
17	Los pichiciegos	100.00
18	El péndulo de Foucault	125.00
19	Cuentos de la selva	55.50
20	El último mohicano	70.99
22	Adios, muñeca	220.99
23	Asi habló Zaratustra	300.00
24	Abbadon, el exterminador	179.90
25	La peste	320.00
26	La dama de las camelias	125.00
29	Tragedias	99.99
30	Otello	269.90
31	El ruido y la furia	360.00
5	Los siete locos	65.00
7	Cien años de soledad	150.00
32	Portnoy's complaint	124.90
34	Nadie vio Matrix	319.90
35	Clean Code	598.90
37	Los cuadernos de don Rigoberto	122.00
38	Otra vuelta de tuerca	147.79
28	Canción de navidad	57.50
40	El talon de hierro	156.00
27	El perro de los Baskerville	79.90
41	El principe y el mendigo	120.00
42	El origen de las especies	754.90
8	1984	87.89
2	Los miserables	355.00
44	Guillermo Tell	110.00
45	Historia	349.90
1	Rayuela - Edicion limitada	479.90
39	America	125.50
33	Thinking in Java	1015.99
3	Martin Fierro	520.00
36	El hombre mediocre	99.00
46	Ingenieria de software	469.90
43	Final del juego	215.00
\.


--
-- Name: producto_codigo_producto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('producto_codigo_producto_seq', 46, true);


--
-- Data for Name: venta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY venta (codigo_venta, codigo_cliente, fecha) FROM stdin;
1	1	2015-09-04
2	2	2015-09-04
3	3	2015-09-04
4	4	2015-09-04
5	7	2015-09-10
6	9	2015-09-10
7	1	2015-09-10
8	18	2015-09-21
9	2	2015-09-21
10	13	2015-09-23
11	15	2015-10-01
12	2	2015-10-01
13	22	2015-12-03
14	23	2015-12-04
15	22	2015-12-07
16	19	2015-12-07
17	19	2015-12-08
18	17	2015-12-08
19	17	2015-12-08
20	16	2015-12-13
21	10	2015-12-15
22	10	2015-12-15
23	27	2015-12-15
\.


--
-- Name: venta_codigo_venta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('venta_codigo_venta_seq', 23, true);


--
-- Name: cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (codigo_cliente);


--
-- Name: detalleventa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY detalleventa
    ADD CONSTRAINT detalleventa_pkey PRIMARY KEY (codigo_venta, codigo_producto);


--
-- Name: producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (codigo_producto);


--
-- Name: venta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (codigo_venta);


--
-- Name: detalleventa_codigoproducto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalleventa
    ADD CONSTRAINT detalleventa_codigoproducto_fkey FOREIGN KEY (codigo_producto) REFERENCES producto(codigo_producto);


--
-- Name: detalleventa_codigoventa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY detalleventa
    ADD CONSTRAINT detalleventa_codigoventa_fkey FOREIGN KEY (codigo_venta) REFERENCES venta(codigo_venta);


--
-- Name: venta_codigocliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY venta
    ADD CONSTRAINT venta_codigocliente_fkey FOREIGN KEY (codigo_cliente) REFERENCES cliente(codigo_cliente);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

