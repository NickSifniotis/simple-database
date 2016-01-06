COMPILER=javac

PACKAGER=jar
P_FLAGS=cvf

TARGET=SimpleDB
VERSION=1.0.0

DATABASE_MANAGER=./DatabaseManager1.0.0.jar

SOURCES=src/NickSifniotis/SimpleDatabase/
CLASSFILES=bin

all:
	mkdir $(CLASSFILES)
	$(COMPILER) -Xlint -classpath $(DATABASE_MANAGER) -verbose -d $(CLASSFILES) $(SOURCES)/*.java $(SOURCES)Columns/*.java
	$(PACKAGER) $(P_FLAGS) $(TARGET)$(VERSION).jar -C $(CLASSFILES) .
	rm -rf $(CLASSFILES)

cleanup:
	rm -rf $(CLASSFILES)
