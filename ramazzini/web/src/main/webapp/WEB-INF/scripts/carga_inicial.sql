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

insert into perfil (cd_perfil, ts_alteracao, ts_inclusao, ic_ativo, nm_perfil, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_perfil'), null, current_timestamp, true, 'medico', null, null);

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

insert into usuario (cd_usuario, ts_alteracao, ts_inclusao, ic_ativo, nm_login, nm_usuario, nm_senha, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_usuario'), null, current_timestamp, true, 'medico@ramazzini.com.br', 'Médico', md5('zini'), null, null);

------------------------ CARGA DA TABELA: USUARIO_PERFIL

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'pedrodias.info@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'thenerbh@gmail.com'), (select cd_perfil from perfil where nm_perfil = 'administrador') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'gerente@ramazzini.com.br'), (select cd_perfil from perfil where nm_perfil = 'gerente') );

insert into usuario_perfil (cd_usuario, cd_perfil) values ( (select cd_usuario from usuario where nm_login = 'medico@ramazzini.com.br'), (select cd_perfil from perfil where nm_perfil = 'medico') );

------------------------ CARGA DA TABELA: PROCEDIMENTOS DO TIPO EXAME CLINICO

insert into procedimento (cd_procedimento, nm_procedimento, sg_procedimento, ic_sistema, tp_exame_clinico, tp_procedimento)
values (nextval('seq_procedimento'), 'EXAME ADMISSIONAL', 'ADM', true, 'ADM', 'EXCLI');

insert into procedimento (cd_procedimento, nm_procedimento, sg_procedimento, ic_sistema, tp_exame_clinico, tp_procedimento)
values (nextval('seq_procedimento'), 'EXAME PERIÓDICO', 'PER', true, 'PER', 'EXCLI');

insert into procedimento (cd_procedimento, nm_procedimento, sg_procedimento, ic_sistema, tp_exame_clinico, tp_procedimento)
values (nextval('seq_procedimento'), 'EXAME MUDANÇA DE FUNÇÃO', 'MF', true, 'MUD', 'EXCLI');

insert into procedimento (cd_procedimento, nm_procedimento, sg_procedimento, ic_sistema, tp_exame_clinico, tp_procedimento)
values (nextval('seq_procedimento'), 'EXAME RETORNO AO TRABALHO', 'RT', true, 'RET', 'EXCLI');

insert into procedimento (cd_procedimento, nm_procedimento, sg_procedimento, ic_sistema, tp_exame_clinico, tp_procedimento)
values (nextval('seq_procedimento'), 'EXAME DEMISSIONAL', 'DEM', true, 'DEM', 'EXCLI');

------------------------ CARGA DA TABELA: ORIGEM DEFICIENCIA

insert into origem_deficiencia (cd_origem_deficiencia, ts_alteracao, ts_inclusao, tp_origem_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_origem_def'), null, current_timestamp, 'AT', null, null); 

insert into origem_deficiencia (cd_origem_deficiencia, ts_alteracao, ts_inclusao, tp_origem_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_origem_def'), null, current_timestamp, 'CG', null, null); 

insert into origem_deficiencia (cd_origem_deficiencia, ts_alteracao, ts_inclusao, tp_origem_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_origem_def'), null, current_timestamp, 'PO', null, null); 

insert into origem_deficiencia (cd_origem_deficiencia, ts_alteracao, ts_inclusao, tp_origem_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_origem_def'), null, current_timestamp, 'AC', null, null); 

insert into origem_deficiencia (cd_origem_deficiencia, ts_alteracao, ts_inclusao, tp_origem_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_origem_def'), null, current_timestamp, 'DC', null, null); 

------------------------ CARGA DA TABELA: ENQUADRAMENTO DEFICIENCIA

insert into enquadramento_deficiencia (cd_enquadramento_deficiencia, ts_alteracao, ts_inclusao, tp_enquadramento_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_enquadramento_deficiencia'), null, current_timestamp, 'DFIS', null, null); 

insert into enquadramento_deficiencia (cd_enquadramento_deficiencia, ts_alteracao, ts_inclusao, tp_enquadramento_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_enquadramento_deficiencia'), null, current_timestamp, 'DAUD', null, null); 

insert into enquadramento_deficiencia (cd_enquadramento_deficiencia, ts_alteracao, ts_inclusao, tp_enquadramento_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_enquadramento_deficiencia'), null, current_timestamp, 'DVIS', null, null); 

insert into enquadramento_deficiencia (cd_enquadramento_deficiencia, ts_alteracao, ts_inclusao, tp_enquadramento_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_enquadramento_deficiencia'), null, current_timestamp, 'DMEN', null, null); 

insert into enquadramento_deficiencia (cd_enquadramento_deficiencia, ts_alteracao, ts_inclusao, tp_enquadramento_deficiencia, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_enquadramento_deficiencia'), null, current_timestamp, 'DMUL', null, null); 

------------------------ CARGA DA TABELA: LIMITACOES DEFICIENCIA MENTAL

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'CO', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'CP', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'HP', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'UR', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'SS', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'HA', null, null); 

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'LA', null, null);

insert into limitacoes_deficiencia_mental (cd_limitacoes_deficiencia_mental, ts_alteracao, ts_inclusao, tp_limitacoes_deficiencia_mental, cd_usuario_alteracao, cd_usuario_inclusao)
values (nextval('seq_limitacoes_deficiencia_mental'), null, current_timestamp, 'TB', null, null);  

------------------------ CARGA DE FUNÇÃO: SEM ACENTO

CREATE OR REPLACE FUNCTION SEM_ACENTO(text)
RETURNS text AS
$BODY$
select
translate($1,'áàâãäéèêëíìïóòôõöúùûüÁÀÂÃÄÉÈÊËÍÌÏÓÒÔÕÖÚÙÛÜçÇ',
        'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC');
$BODY$
LANGUAGE 'sql' IMMUTABLE STRICT;
