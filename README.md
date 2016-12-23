pointex
=======

**deprecated** Daphne and HISinOne started to support csv/Excel exports which
makes this script obsolete.

Point extractor for Daphne

Requires maven version 3 and a Java 7 compiler.

Compilation
-----------

    $ mvn package

Run
---------

Just run the jar:

    $ java -jar target/pointex-0.0.1-SNAPSHOT-jar-with-dependencies.jar

The html file(!) name of the overview website (with the points of the exercises)
has to be given as first argument.

It can print either XML or CSV. CSV can be easily imported in Excel.
