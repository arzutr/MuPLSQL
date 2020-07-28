CREATE OR REPLACE PACKAGE school.pkg_mutation AS
    PROCEDURE run_testcase_prc (
        piov_object_name      IN   VARCHAR2,
        piov_new_object_name  IN   VARCHAR2,
        pion_mutant_id        IN   NUMBER,
        poov_result           OUT  VARCHAR2
    );

END pkg_mutation;
/
