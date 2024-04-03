
 ---------- 创建表空间MOP ---------
 
 create tablespace MOF 
 datafile 'H:\app\Administrator\oradata\orcl\MOF.dbf' 
 size 1024M 
 autoextend on next 50M maxsize unlimited;
 
 ---------- 创建用户支付和单位预算相关用户，并赋予DBA权限 ---------
 
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
 
 
 --------- Recent断开用户连接，删除用户（JWZF2017 JWBMYS2017） ---------
 
 drop user JWBMYS2017 cascade;
 
 drop user JWHS20152017 cascade; 
 
 --------- 在Windows DOS中导入数据库（JWZF2017 JWBMYS2017） --------
 
 imp JWZF2014/1@ORCL FILE = G:\金湾审计数据\JWZF2014\jwqzf20142018-05-29.dmp LOG = G:\金湾审计数据\JWZF2014\jwzf214_log.txt FULL = Y  
 
 imp JWZF2015/1@ORCL FILE = G:\金湾审计数据\JWZF2015\jwqzf20152018-05-29.dmp LOG = G:\金湾审计数据\JWZF2015\jwzf215_log.txt FULL = Y  
 
 imp JWZF2016/1@ORCL FILE = G:\金湾审计数据\JWZF2016\jwqzf20162018-05-29.dmp LOG = G:\金湾审计数据\JWZF2016\jwzf216_log.txt FULL = Y 
 
 imp JWZF2017/1@ORCL FILE = I:\珠海市金湾区审计局\金湾审计数据\JWZF2017\jwqzf20172018-05-29.dmp LOG = I:\珠海市金湾区审计局\金湾审计数据\JWZF2017\jwzf2017_log.txt FULL = Y
  
 --imp JWBMYS2017/1@ORCL FILE = G:\金湾审计数据\JWBMYS2017\440404_bmys_2017_201712.dmp log = G:\金湾审计数据\JWBMYS2017\YS_log.txt FULL=Y
 
 --imp JWBMYS2017/1@ORCL FILE = G:\金湾审计数据\JWBMYS2017\cs_功能分类表2017.dmp log = G:\金湾审计数据\JWBMYS2017\GN_log.txt FULL=Y
 
 imp JWZF2017/1@ORCL FILE = G:\金湾审计数据\JWZF2017\440404_CZYTH_2017_201712.dmp log = G:\金湾审计数据\JWZF2017\ZF_log.txt FULL=Y
 
 imp JWZF2017/1@ORCL FILE = G:\金湾审计数据\JWZF2017\cs_功能分类表2017.dmp log = G:\金湾审计数据\JWZF2017\GN_log.txt FULL=Y
 
 imp JWHS20152018/1@ORCL FILE = J:\珠海市金湾区审计局\金湾审计数据\数据库\JWHS20152018.dmp LOG = J:\珠海市金湾区审计局\金湾审计数据\数据库\JWHS20152018_LOG.txt FULL=Y
 
 ---------- 金湾区核算系统数据导入方式 --------
 ----------- 创建表空间YONYOU ----------
 create tablespace YONYOU 
 logging datafile 'H:\app\Administrator\oradata\orcl\YONYOU.DBF' 
 size 3072m 
 autoextend on next 50M maxsize unlimited;

 ---------创建用户JWHS2019,赋予dba权限 ---------
 create user JWHS20152018 identified by 1 default tablespace YONYOU Temporary TABLESPACE Temp;
 
 grant dba to JWHS20152018;


 ----------导入数据库 将核算库放到相应目录下：H:\app\Administrator\admin\orcl\dpdump 在DOS窗口中输入命令----------
 impdp JWHS2017/1 DIRECTORY=DATA_PUMP_DIR DUMPFILE=440404_JZHS-CZZH_2017_201712.dmp SCHEMAS=JW2015
 
 ---------- 创建用户JWHS201520180528并赋予DBA权限 ---------
 
 create user JWHS20152017 identified by 1
 default tablespace YONYOU
 quota unlimited on YONYOU;
 
 grant dba to JWHS20152017;
 
 ---------- 导入数据 ----------
 
 imp JWHS20152017/1@ORCL FILE = G:\金湾审计数据\JWHS20152017\jwhs2015-20180528.dmp LOG = G:\金湾审计数据\JWHS20152017\JWHS20152017_log.txt FULL = Y
 --------------------
 
 
 

 ---------- 前山支付系统数据导入 ----------
 
 ----------创建表空间 ----------
 
 create tablespace QSSJ
 datafile 'H:\app\Administrator\oradata\orcl\QSSJ.dbf'
 size 1024M
 autoextend on next 50M maxsize unlimited;

 ----------- 创建前山支付用户 ---------- 
 create user QSZF identified by 1
 default tablespace QSSJ
 quota unlimited on QSSJ;
 
 grant dba to QSZF;
 
 ---------- 导入前山支付系统数据 ---------
 
 imp QSZF/1@orcl file = I:\珠海市金湾区审计局\金湾审计数据\QSZF\前山支付.dmp log = I:\珠海市金湾区审计局\金湾审计数据\QSZF\前山支付_log.txt full = Y
 
 imp QSZF/1@orcl file = I:\珠海市金湾区审计局\金湾审计数据\QSZF\前山用款计划.dmp log = I:\珠海市金湾区审计局\金湾审计数据\QSZF\前山用款计划_log.txt full = Y
