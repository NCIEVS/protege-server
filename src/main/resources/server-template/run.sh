#!/bin/sh

cd `dirname $0`

java -Xmx4000M -Xms1500M \
     -Dorg.protege.owl.server.configuration=server-configuration.json \
     -cp "lib/*:bindles/*" \
     org.protege.editor.owl.server.http.HTTPServer
