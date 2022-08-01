--Roles
INSERT INTO db_propets.roles (id, name) VALUES ('1','ROLE_ADMIN');
INSERT INTO db_propets.roles (id, name) VALUES ('2','ROLE_VETERINARY');
INSERT INTO db_propets.roles (id, name) VALUES ('3','ROLE_USER');

--Races
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','CRIOLLO','Criollo',1);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','PITBUL','Pitbul',2);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','TERRIER','Terrier',3);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','SAN_BERNARDO','San Bernardo',4);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','ROTTWEILER','Rottweiler',5);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','LABRADOR','Labrador',6);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'0','DOBERMANN','Dobermann',7);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','CHIHUAHAU','Chihuahua',8);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','BULLDOG','Bulldog',9);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'1','BOXER','Boxer',10);
INSERT INTO db_propets.races (enabled,name,title,id) VALUES (b'0','HUSKY','Husky',11);

--Users
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('juan@gmail.com','Lopez',1,'Juan','$2a$10$8eKQvHZ5vtWY8tkrgoIAGOalnfkQT4HzlLI/uXam8c2.mp45Cx8RS','3165546631');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('diego@gmail.com','Ardila',1,'Diego','$2a$10$8eKQvHZ5vtWY8tkrgoIAGOalnfkQT4HzlLI/uXam8c2.mp45Cx8RS','3165546632');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('luis@gmail.com','Perdomo',1,'Luis','$2a$10$GGYyk2MfH9ou.iTdmRhLp.XAu4AApflrWMIEUbxWYzyY5VJrDCeRi','3165546633');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('sebastian@gmail.com','Lara',1,'Sebastian','$2a$10$GGYyk2MfH9ou.iTdmRhLp.XAu4AApflrWMIEUbxWYzyY5VJrDCeRi','3165546634');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('ricardo@gmail.com','Ramirez',1,'Ricardo','$2a$10$ONtbCmINe7E3a6j5CAZfCumDl2Gvjm7IaN4LjKA0gMygL/nb7yFwW','3165546635');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('david@gmail.com','Gonzales',1,'David','$2a$10$ONtbCmINe7E3a6j5CAZfCumDl2Gvjm7IaN4LjKA0gMygL/nb7yFwW','3165546636');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('jose@gmail.com','Blanco',1,'Jose','$2a$10$9d80CIrjEzYwsH5gWZ4.g.RJNS1oKQgCoa2axG2DnTDrh19YBOpK2','3165546637');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('daniel@gmail.com','Martinez',1,'Daniel','$2a$10$9d80CIrjEzYwsH5gWZ4.g.RJNS1oKQgCoa2axG2DnTDrh19YBOpK2','3165546638');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('kevin@gmail.com','Correa',1,'Kevin','$2a$10$7bQo2xZQzxXEQAZSTpGHM.IDfOZhPbGTGabWfDb9DM6EaTDaXVdwi','3165546639');
INSERT INTO db_propets.users (email, lastname, enabled, name, password, phone) VALUES('karen@gmail.com','Toro',1,'Laura','$2a$10$7bQo2xZQzxXEQAZSTpGHM.IDfOZhPbGTGabWfDb9DM6EaTDaXVdwi','3165546640');

--Users-Roles
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('juan@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('juan@gmail.com',1);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('diego@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('luis@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('sebastian@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('ricardo@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('david@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('jose@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('daniel@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('kevin@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('kevin@gmail.com',2);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('karen@gmail.com',3);
INSERT INTO db_propets.users_roles (user_email, roles_id) VALUES('karen@gmail.com',2);

--Pets
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('1648ce27-0468-4d3b-be2c-4d10224aecaf','Roky',15,11,'diego@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('e4b24c80-1224-411a-91fe-43609b778f73','Lucas',12,1,'diego@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('c54f581d-19d8-403f-9bde-a129d0a86292','Toto',24,2,'diego@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('232123b8-c1ed-475f-8fda-4a360de6b3c5','Lulu',26,3,'diego@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('70cbfdcd-d132-4e61-b528-538602274ce2','Toreto',23,4,'diego@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('578f512b-f7a9-42dd-825a-c8f9250e01fa','Kira',23,5,'luis@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('8f4f6a85-4506-4e4e-a79c-a9224674787b','Sami',25,6,'luis@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('b32ee135-489a-4d85-92fa-b9efc170462e','Minino',4,7,'luis@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('ed99ee42-86ec-4b6b-b037-dbb71808f0e4','Michina',64,8,'luis@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('abec0f6c-900f-4a6f-821f-49fb13966a15','Toby',3,9,'luis@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('7b35004d-6fc0-4b34-a10e-a310dc43cc77','Safiro',3,10,'sebastian@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('5fd1aa0d-a730-4e84-8d0b-b935c1cb52c2','Celeste',5,11,'sebastian@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('ede07b7c-5ffe-4d10-8505-b3f89c0a0fa4','Can',42,1,'sebastian@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('fbaa1a2b-091b-499c-a5dd-1927361ac3ba','Akatsuki',32,2,'sebastian@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('834da899-705e-4080-803f-6ea8b47cf16d','Negro',7,3,'sebastian@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('6211b942-cd35-4323-92d9-3057871f7eb7','Roco',3,4,'ricardo@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('134a372c-2e22-4f0a-b6ac-23b13038ff5c','Lala',2,5,'ricardo@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('f0636d7f-d80e-47f1-9694-357a8127b071','Tomas',65,6,'ricardo@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('8f9df6fc-3586-429a-99ae-cee70d13c9fe','Tomy',75,7,'ricardo@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('cb40a9c6-4e9d-465e-b8e8-7361baf0ed10','Tangiro',27,8,'ricardo@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('8986a73b-5c52-484e-9c24-6442a46710e5','Yogur',16,9,'david@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('2c530e26-7c58-402a-acec-dd2198b9585a','Li',17,10,'david@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('ea9eec4c-37fe-4a59-bfb4-df3e53df9b11','Run',15,11,'david@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('9836fea3-3912-4521-b7ca-f89c1e6ef712','Kakio',16,10,'david@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('765307c4-90f3-4a78-9b1c-75e8f1f02fa0','Rojo',15,2,'david@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('53686c55-f0d7-4296-bab9-8e03b4fa0b6b','Chandoso',16,3,'jose@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('20ae5cbf-0830-45ca-8713-f295da84b1c4','Pirrito',17,4,'jose@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('c06213a0-74c7-4588-a166-aced30c944c1','Juan',18,5,'jose@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('cb42477e-a021-4d90-9b9d-ec6eb6d6266c','Jose',19,6,'jose@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('3da74e14-1fbc-4a43-bd46-ca0bb8ee6bd4','Miel',7,7,'jose@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('4be573bd-c6a2-49fe-976c-862b98268044','Cielo',9,8,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('c46650f4-7ac3-4e58-b4ba-4dc9f26b385b','Sol',7,9,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('168f7926-81ef-40fd-b2b2-4b53981aab73','Sapo',6,10,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('fec81495-d01f-4b1b-aaf8-c9be431568ac','Sopa',8,11,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('4bfcf006-e9a6-4268-af8e-c87b77565169','Papu',9,1,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('0ca8550a-8194-4484-afa5-568a80bf9cf2','Susi',9,8,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('4db37f14-df84-443c-bf3b-ca32a4835d4e','Sula',7,9,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('77c8d3de-2049-446c-a985-82df0eac57a2','Chocho',6,10,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('7843b2f0-cd36-4fa7-8054-3170b55ee595','Cucarron',8,11,'daniel@gmail.com')
INSERT INTO db_propets.pets (id, name, peso, race_id, email) VALUES('7d818920-fb95-465a-afde-6d4662d4403f','Panda',9,1,'daniel@gmail.com')