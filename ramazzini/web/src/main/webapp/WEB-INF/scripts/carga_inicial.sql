/*----------------------------------------------------------------
CARGA INICIAL DO SISTEMA
----------------------------------------------------------------*/

------------------------ CARGA DA TABELA: MODULO

insert into modulo (cd_modulo, ts_alteracao, ts_inclusao, ic_ativo, nm_modulo, cd_usuario_alteracao, cd_usuario_inclusao) 
values (nextval('seq_modulo'), null, current_timestamp, true, 'usuario', null, null);

insert into modulo (cd_modulo, ts_alteracao, ts_inclusao, ic_ativo, nm_modulo, cd_usuario_alteracao, cd_usuario_inclusao) 
values (nextval('seq_modulo'), null, current_timestamp, true, 'home', null, null);

------------------------ CARGA DA TABELA: TELA

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'pesquisarUsuario.jsf', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'visualizarUsuario.jsf', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'incluirUsuario.jsf', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'alterarUsuario.jsf', false, null, null, (select cd_modulo from modulo where nm_modulo = 'usuario') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'home.jsf', true, null, null, (select cd_modulo from modulo where nm_modulo = 'home') );

insert into tela (cd_tela, ts_alteracao, ts_inclusao, ic_ativa, nm_tela, ic_publico, cd_usuario_alteracao, cd_usuario_inclusao, cd_modulo)
values (nextval('seq_tela'), null, current_timestamp, true, 'acessoNaoAutorizado.jsf', true, null, null, (select cd_modulo from modulo where nm_modulo = 'home') );

------------------------ CARGA DA TABELA: ACAO

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (nextval('seq_acao'), null, current_timestamp, true, 'visualizarUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario.jsf'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (nextval('seq_acao'), null, current_timestamp, true, 'incluirUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario.jsf'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (nextval('seq_acao'), null, current_timestamp, true, 'alterarUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario.jsf'));

insert into acao (cd_acao, ts_alteracao, ts_inclusao, ic_ativo, nm_acao, cd_usuario_alteracao, cd_usuario_inclusao, cd_tela)
values (nextval('seq_acao'), null, current_timestamp, true, 'excluirUsuario', null, null, (select cd_tela from tela where nm_tela = 'pesquisarUsuario.jsf'));

------------------------ CARGA DA TABELA: PERFIL

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_perfil'), null, current_timestamp, true, 'administrador', null, null); 

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_perfil'), null, current_timestamp, true, 'atendimento', null, null);

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_perfil'), null, current_timestamp, true, 'gerente', null, null); 

------------------------ CARGA DA TABELA: PERFIL_TELA

-- Obs 1: Telas públicas não precisam ser associadas a perfis
-- Obs 2: Perfil "administrador" não precisa ter permissões inseridas
-- Obs 3: Para demais perfis, logar como administrador e definir os acessos

------------------------ CARGA DA TABELA: USUARIO

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_usuario'), null, current_timestamp, true, 'pedrodias.info@gmail.com', 'Pedro', md5('zini'), null, null);

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_usuario'), null, current_timestamp, true, 'thenerbh@gmail.com', 'Thener', md5('zini'), null, null);

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_usuario'), null, current_timestamp, true, 'gerente@ramazzini.com.br', 'Gerente', md5('zini'), null, null);

------------------------ CARGA DA TABELA: USUARIO_PERFIL

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'pedrodias.info@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'thenerbh@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'gerente@ramazzini.com.br'), (select cd_perfil from perfil where nm_perfil = 'gerente') );