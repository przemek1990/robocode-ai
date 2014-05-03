Robocode artificial intelligence

========================================================

Library:
Robocode - http://robocode.sourceforge.net/
Robocode is a programming game, where the goal is to develop a robot battle tank to battle against other tanks in Java or .NET.
The robot battles are running in real-time and on-screen.

Java Machine Learning Library - http://java-ml.sourceforge.net/

======================= SETUP ENV =====================
java -jar {project-dir}\plarfrom robocode-1.9.2.0.jar
mvn install:install-file -Dfile={project-dir}\libs\robocode.jar -DgroupId=robocode -DartifactId=robocode -Dversion=1.0 -Dpackaging=jar
mvn clean install

======================= CREATE BATTLE ==================
# Open Robocode
# Robot -> Import robot or team
# Choose jar for {project-dir}\target
# To create new battle: Battle -> New