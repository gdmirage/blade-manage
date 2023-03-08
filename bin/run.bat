@echo off
echo.
echo [信息] 使用Jar命令运行Web工程。
echo.

cd %~dp0
cd ../blade-admin/target

set JAVA_OPTS=-Xms256m -Xmx256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% blade-admin.jar

cd bin
pause