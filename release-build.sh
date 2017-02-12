cd /home/ammar/NetBeansProjects/TagAPI_3/
cd dist/
cd lib/
jar xf commons-io-2.5.jar
jar xf json-20160212.jar
jar xf json-simple-1.1.1.jar
jar xf HEX_MOD.jar
jar xf zt-zip-1.11.jar
cd ../lib/; jar xf ../TagAPI_3.jar
rm commons-io-2.5.jar
rm json-20160212.jar
rm json-simple-1.1.1.jar
rm HEX_MOD.jar
rm zt-zip-1.11.jar
cd ../
rm TagAPI_3.jar
cd lib/
zip TagAPI_3.zip * -r
mv TagAPI_3.zip ../TagAPI_3.jar
pwd
cd ../
rm -rf lib
