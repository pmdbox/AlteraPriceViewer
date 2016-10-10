#!/bin/bash
cd /home/maryan/IdeaProjects/AlteraPriceViewer/out/production/AlteraPriceViewer
CLASSPATH=/home/maryan/IdeaProjects/AlteraPriceViewer/lib/jOpenDocument-1.3.jar:/home/maryan/IdeaProjects/AlteraPriceViewer/lib/sqlite-jdbc-3.14.2.1.jar:/home/maryan/IdeaProjects/AlteraPriceViewer/out/production/AlteraPriceViewer/
export CLASSPATH
echo $CLASSPATH
java AlteraPriceViewer


