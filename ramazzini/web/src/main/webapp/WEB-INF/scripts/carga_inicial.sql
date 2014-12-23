/*----------------------------------------------------------------
CARGA INICIAL DO SISTEMA
----------------------------------------------------------------*/

-------- CARGA DA TABELA: MODULO

insert into modulo (cd_modulo, ts_alteracao, ts_inclusao, ic_ativo, nm_modulo, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao) 
values (1, null, current_timestamp, true, 'usuario', false, null, null);

insert into modulo (cd_modulo, ts_alteracao, ts_inclusao, ic_ativo, nm_modulo, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao) 
values (2, null, current_timestamp, true, 'home', true, null, null);

-------- CARGA DA TABELA: TELA

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (1, null, current_timestamp, true, 'pesquisarUsuario', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (2, null, current_timestamp, true, 'visualizarUsuario', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (3, null, current_timestamp, true, 'incluirUsuario', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (4, null, current_timestamp, true, 'alterarUsuario', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (5, null, current_timestamp, true, 'excluirUsuario', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativo, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (6, null, current_timestamp, true, 'home', false, null, null, (select cd_modulo from modulo where nm_modulo = 'home') );

-------- CARGA DA TABELA: ACAO

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (1, null, current_timestamp, true, 'visualizarUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (2, null, current_timestamp, true, 'incluirUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (3, null, current_timestamp, true, 'alterarUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (4, null, current_timestamp, true, 'excluirUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario'));


-------- CARGA DA TABELA: PERFIL

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (1, null, current_timestamp, true, 'administrador', null, null); 

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (2, null, current_timestamp, true, 'atendimento', null, null); 

-------- CARGA DA TABELA: PERFIL_TELA

--> Perfil "administrador" não precisará ter permissões nas tabelas.

-------- CARGA DA TABELA: USUARIO

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (1, null, current_timestamp, true, 'pedrodias.info@gmail.com', 'Pedro', md5('zini'), null, null);

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (2, null, current_timestamp, true, 'thenerbh@gmail.com', 'Thener', md5('zini'), null, null);

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (3, null, current_timestamp, true, 'atendimento@ramazzini.com.br', 'Atendimento', md5('zini'), null, null);

-------- CARGA DA TABELA: USUARIO_PERFIL

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'pedrodias.info@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'thenerbh@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'atendimento@ramazzini.com.br'), (select cd_perfil from perfil where nm_perfil = 'atendimento') );