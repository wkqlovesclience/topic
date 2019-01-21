#!/bin/bash
set -x
#LANG="en_US.utf-8"
cd /app/source/Topic/
#svn up --username updatecode --password updatecode
#LANG="zh_CN.GBK"
mvn clean
mvn install
sleep 5
rm -rf /app/deploy/topic/admin
mkdir -p /app/deploy/topic/admin
\cp /app/source/Topic/target/btoc_topic.war /app/deploy/topic/admin/
cd /app/deploy/topic/admin
jar -xvf btoc_topic.war
ln -sv /home01/website_2011/image_2011_04/topic /app/deploy/topic/admin/img
rm -rf btoc_topic.war
