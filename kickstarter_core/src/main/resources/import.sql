--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (1, b'1', 'Administrador', 'admin', 'admin', 0, 0);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (2, b'0', 'Juan Perez', 'juan', 'juan', 200.0, 50);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (3, b'0', 'Pedro Rodriguez', 'pedro', 'pedro', 350.0, 40);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (4, b'0', 'Alberto Garcia', 'alberto', 'alberto', 500.0, 5);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (5, b'0', 'Fernando Gomez', 'fernando', 'fernando', 1350.0, 0);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (6, b'0', 'Marcos Lopez', 'marcos', 'marcos', 1000.0, 65);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (7, b'0', 'Marcela Alvarez', 'marcela', 'marcela', 600.0, 20);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (8, b'0', 'Florencia Martinez', 'florencia', 'florencia', 200.0, 10);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (9, b'0', 'Agustin Navarro', 'agustin', 'agustin', 220.0, 5);
INSERT INTO `User` (`id_user`, `administrator`, `name`, `username`, `password`, `money`, `successPoints`) VALUES (10, b'0', 'Romina Sanchez', 'romina', 'romina', 550.0, 30);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `userrole`
--

INSERT INTO `UserRole` (`id_user_role`, `id_user`, `id_project`) VALUES (1, 2, null);
INSERT INTO `UserRole` (`id_user_role`, `id_user`, `id_project`) VALUES (2, 4, null);
INSERT INTO `UserRole` (`id_user_role`, `id_user`, `id_project`) VALUES (3, 6, null);
INSERT INTO `UserRole` (`id_user_role`, `id_user`, `id_project`) VALUES (4, 8, null);
INSERT INTO `UserRole` (`id_user_role`, `id_user`, `id_project`) VALUES (5, 10, null);


-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `creatorrole`
--

INSERT INTO `CreatorRole` (`id_user_role`) VALUES (1);
INSERT INTO `CreatorRole` (`id_user_role`) VALUES (2);
INSERT INTO `CreatorRole` (`id_user_role`) VALUES (3);
INSERT INTO `CreatorRole` (`id_user_role`) VALUES (4);
INSERT INTO `CreatorRole` (`id_user_role`) VALUES (5);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `projectstate`
--

INSERT INTO `ProjectState` (`id_project_state`, `type`) VALUES (1, 'Active');
INSERT INTO `ProjectState` (`id_project_state`, `type`) VALUES (2, 'Active');
INSERT INTO `ProjectState` (`id_project_state`, `type`) VALUES (3, 'Cancelled');
INSERT INTO `ProjectState` (`id_project_state`, `type`) VALUES (4, 'Finished');
INSERT INTO `ProjectState` (`id_project_state`, `type`) VALUES (5, 'Suspect');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `project`
--

INSERT INTO `Project` (`id_project`, `type`, `id_creator_role`, `id_project_state`) VALUES (1, 'Movie', 1, 1);
INSERT INTO `Project` (`id_project`, `type`, `id_creator_role`, `id_project_state`) VALUES (2, 'Game', 2, 2);
INSERT INTO `Project` (`id_project`, `type`, `id_creator_role`, `id_project_state`) VALUES (3, 'Movie', 3, 3);
INSERT INTO `Project` (`id_project`, `type`, `id_creator_role`, `id_project_state`) VALUES (4, 'Game', 4, 4);
INSERT INTO `Project` (`id_project`, `type`, `id_creator_role`, `id_project_state`) VALUES (5, 'Movie', 5, 5);


UPDATE `UserRole` SET `id_project` = 1 WHERE `id_user_role` = 1;
UPDATE `UserRole` SET `id_project` = 2 WHERE `id_user_role` = 2;
UPDATE `UserRole` SET `id_project` = 3 WHERE `id_user_role` = 3;
UPDATE `UserRole` SET `id_project` = 4 WHERE `id_user_role` = 4;
UPDATE `UserRole` SET `id_project` = 5 WHERE `id_user_role` = 5;

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `Section`
--

INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (1, 'Nombre', 'Mi pobre angelito', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (2, 'Genero', 'Comedia', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (3, 'Objetivo', 'Crear una pelicula comica', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (4, 'Descripcion', 'Un ni�o de 8 a�os es dejado accidentalemente en su casa mientras su familia vuela a Francia para pasar la navidad. El ni�o debera defender su casa ante unos ladrones no muy experimentados.', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (5, 'Dinero requerido', '50000', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (6, 'Guion', 'El guion esta en proceso de redaccion', 1);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (7, 'Nombre', 'Tetris para Android', 2);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (8, 'Nombre en codigo', 'TFA-V1', 2);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (9, 'Objetivo', 'Crear una version del tetris para dispositivos Android', 2);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (10, 'Descripcion', 'Distintas figuras geometricas caen desde la parte superior de la pantalla. El jugador puede decidir la rotacion de la pieza y en que lugar debe caer. Cuando una linea horizontal se completa, esa linea desaparece y todas las piezas que estan por encima descienden una posicion, liberando espacio de juego.', 2);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (11, 'Dinero requerido', '10000', 2);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (12, 'Nombre', 'El secreto de sus ojos', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (13, 'Genero', 'Drama', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (14, 'Objetivo', 'Filmar una pelicula de genero dramatico con actores argentinos', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (15, 'Descripcion', 'Benjamin Esposito es oficial en un juzgado de instruccion de Buenos Aires y esta a punto de retirarse. Obsesionado por un brutal asesinato ocurrido treinta a�os antes, decide escribir una novela sobre el caso, del cual fue testigo y protagonista. Reviviendo el pasado, viene tambien a su meoria el recuerdo de una mujer, a quien ha amado en silencio durante todos esos a�os.', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (16, 'Dinero requerido', '300000', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (17, 'Guion', 'El guion esta en proceso de redaccion', 3);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (18, 'Nombre', 'Math Wizard', 4);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (19, 'Nombre en codigo', 'MW-V1', 4);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (20, 'Objetivo', 'Crear un juego tipo QUIZ matem�tico para dispositivos Android', 4);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (21, 'Descripcion', 'El juego presentar� distintas operaciones matematicas y el jugador deber� elegir la opci�n que contenga la respuesta correcta en el menor tiempo posible.', 4);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (22, 'Dinero requerido', '12000', 4);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (23, 'Nombre', 'La familia de mi novia', 5);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (24, 'Genero', 'Comedia', 5);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (25, 'Objetivo', 'Crear una pelicula comica', 5);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (26, 'Descripcion', 'Greg es un enefermero que quiere proponer matrimonio a su novia Pam. Antes del enlace, la pareja tendra que visitar a los padres de ella: el ex agente de la CIA Jack Byrnes y su mujer Dina. Pero no sera nada facil: ademas de aguantar a un gato problematico y al ex novia de su futura esposa, Greg tendra que lidiar con su suegro. Este, que quiere asegurarse que su hija se case con el hombre correcto, utilizara todo tipo de juegos mentales, detectores de mentiras y todo tipo de situaciones extra�as para presionarlo y ponerlo a prueba.', 5);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (27, 'Dinero requerido', '100000', 5);
INSERT INTO `Section` (`id_section`, `name`, `content`, `id_project`) VALUES (28, 'Guion', 'El guion esta en proceso de desarrollo', 5);
