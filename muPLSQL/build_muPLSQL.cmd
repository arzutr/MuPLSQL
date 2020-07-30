REM jar file creation with command line
REM compile with jar name 

set java_path= c:\Program Files\Java\jdk1.8.0_25\bin;.\lib\ora18i.jar;\lib/ojdbc6.jar;spinatplsql_parser.jar 

 javac -cp $javapath  ./src/*.java  -d ./build_classes/
 
 jar cvfm ./deploy/muPLSQL.jar -C ./build_classes/