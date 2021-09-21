
CREATE TABLE tmutation_otypes
AS
   (SELECT DISTINCT TYPE_NAME, owner
      FROM ALL_TYPES
     WHERE OWNER = '${object_name}' AND TYPECODE = 'COLLECTION')
   UNION
   (SELECT DISTINCT
           TRIM (
              SUBSTR (
                 UPPER (TEXT),
                 INSTR (UPPER (TEXT), 'TYPE') + 4,
                 INSTR (
                    SUBSTR (UPPER (TEXT),
                            INSTR (UPPER (TEXT), 'TYPE') + 4,
                            LENGTH (UPPER (TEXT))),
                    ' IS ')))
              AS type_name,
           owner
      FROM ALL_SOURCE
     WHERE     OWNER = '${object_name}'
           AND TYPE IN ('FUNCTION', 'PROCEDURE', 'PACKAGE BODY')
           AND UPPER (TEXT) LIKE '%TABLE OF%')
   UNION
   (SELECT DISTINCT
           TRIM (
              SUBSTR (
                 UPPER (TEXT),
                 INSTR (UPPER (TEXT), 'TYPE') + 4,
                 INSTR (
                    SUBSTR (UPPER (TEXT),
                            INSTR (UPPER (TEXT), 'TYPE') + 4,
                            LENGTH (UPPER (TEXT))),
                    ' IS ')))
              AS type_name,
           owner
      FROM all_source
     WHERE     owner = '${object_name}'
           AND TYPE IN ('FUNCTION', 'PROCEDURE', 'PACKAGE BODY')
           AND UPPER (TEXT) LIKE '%VARRAY%')
           
           
create table as tmutation_typedefinition as
SELECT DISTINCT
       name,
       TRIM (SUBSTR (UPPER (TEXT), INSTR (UPPER (TEXT), ' ') + 1))
          AS declaration
  FROM all_source a, tmutation_otypes b
 WHERE     a.owner = b.owner
       AND TYPE IN ('FUNCTION', 'PROCEDURE', 'PACKAGE BODY')
       AND UPPER (TEXT) LIKE '% ' || b.type_name || '%;%'
       AND UPPER (TEXT) NOT LIKE '%TYPE%'




 

CREATE TABLE mutation.TMUTATION_DICTIONARY
(
   owner_name     VARCHAR2 (100),
   object_name    VARCHAR2 (100),
   method_name    VARCHAR2 (100),
   variablename   VARCHAR2 (100),
   datatype       VARCHAR2 (100),
   is_constant    VARCHAR2 (1)
);

