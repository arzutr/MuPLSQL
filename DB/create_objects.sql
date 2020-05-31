CREATE TABLE TMUTATION
(
MUTANTION_OPERATOR_ID VARCHAR2(50)
);


CREATE TABLE [SCHEMA_NAME].TMUTATION_CONFIG
(
  PATTERN                    VARCHAR2(2000 BYTE),
  EXEC_STR                   VARCHAR2(4000 BYTE),
  MNAME                      VARCHAR2(2000 BYTE),
  MUTATION_OPERATOR_ID       NUMBER,
  MAIN_MUTATION_OPERATOR_ID  NUMBER,
  NOT_EXEC                   VARCHAR2(2000 BYTE)
);

CREATE TABLE [SCHEMA_NAME].TMUTATION_OPERATIONS
(
  OBJECT_NAME           VARCHAR2(200 BYTE),
  NEW_OBJECT_NAME       VARCHAR2(200 BYTE),
  MUTATION_OPERATOR_ID  NUMBER,
  MNAME                 VARCHAR2(2000 BYTE),
  CHANGED_LINE          NUMBER,
  MUTANT_ID             NUMBER,
  CREATE_DATE           DATE                    DEFAULT sysdate,
  IS_RUN                NUMBER                  DEFAULT 0
);

CREATE TABLE [SCHEMA_NAME].TMUTATION_OPERATORS
(
  OPERATOR_NAME         VARCHAR2(200 BYTE),
  MUTATION_OPERATOR_ID  NUMBER,
  SOURCE                VARCHAR2(2000 BYTE),
  TYPE                  VARCHAR2(500 BYTE),
  MAIN_TYPE             VARCHAR2(500 BYTE),
  STATUS                VARCHAR2(500 BYTE)
);



CREATE TABLE [SCHEMA_NAME].TMUTATION_TESTCASE
(
  OBJECT_NAME    VARCHAR2(200 BYTE),
  TESTCASE       VARCHAR2(4000 BYTE),
  TESTCASE_ID    NUMBER,
  TESTCASE_TYPE  VARCHAR2(5 BYTE),
  IS_VALID       NUMBER                         DEFAULT 0
);


CREATE TABLE [SCHEMA_NAME].TMUTATION_TESTCASE_EXEC
(
  OBJECT_NAME        VARCHAR2(200 BYTE),
  TESTCASE_RESULT    VARCHAR2(4000 BYTE),
  TESTCASE_ERR_LINE  NUMBER,
  TESTCASE_STATUS    NUMBER,
  MUTANT_ID          NUMBER,
  TESTCASE_ID        NUMBER,
  NEW_OBJECT_NAME    VARCHAR2(500 BYTE),
  CREATE_DATE        DATE                       DEFAULT sysdate
);