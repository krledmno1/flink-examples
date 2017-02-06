# flink-examples

I started by creating an empty project with

```bash

mvn archetype:generate                               \
      -DarchetypeGroupId=org.apache.flink              \
      -DarchetypeArtifactId=flink-quickstart-scala     \
      -DarchetypeCatalog=https://repository.apache.org/content/repositories/snapshots/ \
      -DarchetypeVersion=1.3-SNAPSHOT

```

Otherwise, just clone this project.

Added to IntelliJ  via File -> New -> Project from Existing Sources...

Download the latest flink binary, extract and run it:

```bash
tar xzf flink-*.tgz   # Unpack the downloaded archive
cd flink-1.1.4
bin/start-local.sh
```

You also may want to put bin/flink aliased to just "flink"

## Example 1

Standard SocketWindowWordCount

Start a local socket on port 9000

```bash
nc -l 9000
```

then start the flink topology

```bash
flink run  -c ch.ethz.test.SocketTextStreamWordCount target/testFlink-1.0-SNAPSHOT.jar localhost 9000
```
Notice that the port numbers need to match.
Then just type strings and look at the counted words in the output via

```bash
tail -f log/flink-*-jobmanager-*.out
```

## Example 2 - Wikipedia

Reimplementation in Scala, exemplifies the use of Wikipedia connector.
Just run it as follows:

```bash
flink run  -c ch.ethz.test.WikiExample target/testFlink-1.0-SNAPSHOT.jar
```
The output shows the relative byte changes per user. 

## Example 3 - Twitter

Reimplementation in Scala, exemplifies the use of Twitter connector.

```bash
flink run  -c ch.ethz.test.TwitterExample target/testFlink-1.0-SNAPSHOT.jar --twitter-source.consumerKey <key> --twitter-source.consumerSecret <secret> --twitter-source.token <token> --twitter-source.tokenSecret <tokenSecret>
```
It reports words as they reach 100 occurences 
