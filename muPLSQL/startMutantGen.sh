export JAVA_HOME=/home/jdk/jdk1.8;
export ORACLE_HOME=/home/app/oracle/product/10g/client;

CLASSPATH=$JAVA_HOME/bin/;$ORACLE_HOME/bin/;${PATH}:/home/lib/muPLSQL.jar;/home/lib/ojdbc6.jar;/home/lib/orai18n.jar;/home/lib/spinatplsql_parser.jar

java -cp ${CLASSPATH} org.muplsql.mg.WinMain  &


echo For running test case execution change properties file

java -cp ${CLASSPATH} org.muplsql.mt.WinMain  &
