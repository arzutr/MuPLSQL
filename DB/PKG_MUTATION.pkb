CREATE OR REPLACE PACKAGE BODY [schema name].PKG_MUTATION
AS


   PROCEDURE RUN_TESTCASE_PRC (piov_object_name       IN     VARCHAR2,
                               piov_new_object_name   IN     VARCHAR2,
                               pion_mutant_id         IN     NUMBER,
                               poov_result            OUT VARCHAR2)
   IS
   
      V_SQL                         VARCHAR2 (4000);
      V_SQL_INSERT_STMT             VARCHAR2 (4000);
      v_temp varchar2(4000); 
     
      
      CURSOR cTestCase (
         piov_object_name       IN VARCHAR2,
         piov_new_object_name   IN VARCHAR2)
      IS
         SELECT object_name, testcase, testcase_id
           FROM tmutation_testcase s
          WHERE     is_valid = 0
                AND object_name = piov_object_name
                AND NOT EXISTS
                           (SELECT new_object_name
                              FROM  TMUTATION_TESTCASE_EXEC
                             WHERE     TESTCASE_ID = s.TESTCASE_ID
                                   AND new_object_name = piov_new_object_name);

   BEGIN
       poov_result:='OK';
   
      FOR r IN cTestCase (piov_object_name, piov_new_object_name)
      LOOP
     
         BEGIN
            V_SQL :=r.TESTCASE;

            V_SQL := REPLACE (V_SQL, 'result:=', 'rresult:=');


            V_SQL := 'declare     rresult varchar2(500);  ' || V_SQL || '  ';
            V_SQL_INSERT_STMT := ' insert into  TMUTATION_TESTCASE_EXEC';
            V_SQL_INSERT_STMT :=
                  V_SQL_INSERT_STMT
               || '(OBJECT_NAME, TESTCASE_RESULT, MUTANT_ID, TESTCASE_ID, NEW_OBJECT_NAME)  values ';
            V_SQL_INSERT_STMT :=
                  V_SQL_INSERT_STMT
               || ' ('''
               || piov_OBJECT_NAME
               || ''',  substr(rresult,1,4000) , '
               || pion_MUTANT_ID
               || ' ,'
               || r.TESTCASE_ID
               || ','''
               || piov_NEW_OBJECT_NAME
               || '''  ); ';
            V_SQL := V_SQL || V_SQL_INSERT_STMT;
            V_SQL := V_SQL || ' end;  ';

            EXECUTE IMMEDIATE V_SQL;

            V_SQL := '';
            V_SQL_INSERT_STMT := '';
           
         EXCEPTION
            WHEN OTHERS
            THEN
            
               BEGIN
                  poov_result:='NOK-EXCEPTION';
                v_temp :=  SUBSTR (SQLERRM || '', 1, 4000);
                  INSERT
                    INTO TMUTATION_TESTCASE_EXEC (OBJECT_NAME,
                                                           TESTCASE_RESULT,
                                                           MUTANT_ID,
                                                           TESTCASE_ID,
                                                           NEW_OBJECT_NAME,
                                                           TESTCASE_STATUS)
                  VALUES (piov_OBJECT_NAME,
                         v_temp,
                          pion_MUTANT_ID,
                          r.TESTCASE_ID,
                          piov_NEW_OBJECT_NAME,
                          -1);
               END;
         END;
      END LOOP;
     
      
   EXCEPTION
      WHEN OTHERS
      THEN
         poov_result:='NOK-EXCEPTION'; -- not used but readability
         RAISE ;
   END;
END PKG_MUTATION;
/
