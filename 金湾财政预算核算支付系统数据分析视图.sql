
 ---------- ������ռ�MOP ---------
 
 create tablespace MOF 
 datafile 'H:\app\Administrator\oradata\orcl\MOF.dbf' 
 size 1024M 
 autoextend on next 50M maxsize unlimited;
 
 ---------- �����û�֧���͵�λԤ������û���������DBAȨ�� ---------
 
 create user JWZF2014 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWZF2014;
 -----------
 create user JWZF2015 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWZF2015;
 -----------
 create user JWZF2016 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWZF2016;
 -----------
 create user JWZF2017 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWZF2017;
 ----------
 create user JWBMYS2017 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWBMYS2017; 
 
 -----------
 create user JWHS20152018 identified by 1
 default tablespace mof
 quota unlimited on mof;
 
 grant dba to JWHS20152018;
 
 
 --------- Recent�Ͽ��û����ӣ�ɾ���û���JWZF2017 JWBMYS2017�� ---------
 
 drop user JWBMYS2017 cascade;
 
 drop user JWHS20152017 cascade; 
 
 --------- ��Windows DOS�е������ݿ⣨JWZF2017 JWBMYS2017�� --------
 
 imp JWZF2014/1@ORCL FILE = G:\�����������\JWZF2014\jwqzf20142018-05-29.dmp LOG = G:\�����������\JWZF2014\jwzf214_log.txt FULL = Y  
 
 imp JWZF2015/1@ORCL FILE = G:\�����������\JWZF2015\jwqzf20152018-05-29.dmp LOG = G:\�����������\JWZF2015\jwzf215_log.txt FULL = Y  
 
 imp JWZF2016/1@ORCL FILE = G:\�����������\JWZF2016\jwqzf20162018-05-29.dmp LOG = G:\�����������\JWZF2016\jwzf216_log.txt FULL = Y 
 
 imp JWZF2017/1@ORCL FILE = I:\�麣�н�������ƾ�\�����������\JWZF2017\jwqzf20172018-05-29.dmp LOG = I:\�麣�н�������ƾ�\�����������\JWZF2017\jwzf2017_log.txt FULL = Y
  
 --imp JWBMYS2017/1@ORCL FILE = G:\�����������\JWBMYS2017\440404_bmys_2017_201712.dmp log = G:\�����������\JWBMYS2017\YS_log.txt FULL=Y
 
 --imp JWBMYS2017/1@ORCL FILE = G:\�����������\JWBMYS2017\cs_���ܷ����2017.dmp log = G:\�����������\JWBMYS2017\GN_log.txt FULL=Y
 
 imp JWZF2017/1@ORCL FILE = G:\�����������\JWZF2017\440404_CZYTH_2017_201712.dmp log = G:\�����������\JWZF2017\ZF_log.txt FULL=Y
 
 imp JWZF2017/1@ORCL FILE = G:\�����������\JWZF2017\cs_���ܷ����2017.dmp log = G:\�����������\JWZF2017\GN_log.txt FULL=Y
 
 imp JWHS20152018/1@ORCL FILE = J:\�麣�н�������ƾ�\�����������\���ݿ�\JWHS20152018.dmp LOG = J:\�麣�н�������ƾ�\�����������\���ݿ�\JWHS20152018_LOG.txt FULL=Y
 
 ---------- ����������ϵͳ���ݵ��뷽ʽ --------
 ----------- ������ռ�YONYOU ----------
 create tablespace YONYOU 
 logging datafile 'H:\app\Administrator\oradata\orcl\YONYOU.DBF' 
 size 3072m 
 autoextend on next 50M maxsize unlimited;

 ---------�����û�JWHS2019,����dbaȨ�� ---------
 create user JWHS20152018 identified by 1 default tablespace YONYOU Temporary TABLESPACE Temp;
 
 grant dba to JWHS20152018;


 ----------�������ݿ� �������ŵ���ӦĿ¼�£�H:\app\Administrator\admin\orcl\dpdump ��DOS��������������----------
 impdp JWHS2017/1 DIRECTORY=DATA_PUMP_DIR DUMPFILE=440404_JZHS-CZZH_2017_201712.dmp SCHEMAS=JW2015
 
 ---------- �����û�JWHS201520180528������DBAȨ�� ---------
 
 create user JWHS20152017 identified by 1
 default tablespace YONYOU
 quota unlimited on YONYOU;
 
 grant dba to JWHS20152017;
 
 ---------- �������� ----------
 
 imp JWHS20152017/1@ORCL FILE = G:\�����������\JWHS20152017\jwhs2015-20180528.dmp LOG = G:\�����������\JWHS20152017\JWHS20152017_log.txt FULL = Y
 --------------------
 
 
 

 ---------- ǰɽ֧��ϵͳ���ݵ��� ----------
 
 ----------������ռ� ----------
 
 create tablespace QSSJ
 datafile 'H:\app\Administrator\oradata\orcl\QSSJ.dbf'
 size 1024M
 autoextend on next 50M maxsize unlimited;

 ----------- ����ǰɽ֧���û� ---------- 
 create user QSZF identified by 1
 default tablespace QSSJ
 quota unlimited on QSSJ;
 
 grant dba to QSZF;
 
 ---------- ����ǰɽ֧��ϵͳ���� ---------
 
 imp QSZF/1@orcl file = I:\�麣�н�������ƾ�\�����������\QSZF\ǰɽ֧��.dmp log = I:\�麣�н�������ƾ�\�����������\QSZF\ǰɽ֧��_log.txt full = Y
 
 imp QSZF/1@orcl file = I:\�麣�н�������ƾ�\�����������\QSZF\ǰɽ�ÿ�ƻ�.dmp log = I:\�麣�н�������ƾ�\�����������\QSZF\ǰɽ�ÿ�ƻ�_log.txt full = Y
