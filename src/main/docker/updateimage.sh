sh clean.sh
mvn -f ../../.. clean package -DskipTests=true
cp ../../../target/timetable-bot-0.0.1-SNAPSHOT.jar ./
docker tag timetable-bot ouiuo/timetable-bot:0.10
docker build -t timetable-bot .
