# Spigot Race
SpigotRace is a Spigot Plugin that allows users to create race courses, and race against friends on the server

## Commands
- /createcourse [course name] - Creates a race course with that name
- /deletecourse [course name] - Deletes a course if you made it with that name
- /addcheckpoint [course name] - Addes a checkpoint at your location if you made the course
- /listcourse - lists all race course
- /createrace [course name] - Creates a race that your friend can join
- /joinrace [course name] - Joins an active race
- /leaverace [course name] - Leave an active race
- /startrace [course name] - Start a race if you started it
- /stoprace [course name] - Stop an ongoing race
- /activeraces - List all actice races
- /editor [course name] - Create an gui editor for a course you made

## Installing
Download the jar from under releases and copy it to your plugin folder.

## Building
To build this plugin you neeed [maven](https://maven.apache.org/install.html) installed </br>
Once maven is installed download the code and open a command line in that folder and run `mvn package` </br>
Then a jar will made in target/ called `Race-1.0-SNAPSHOT.jar` copy that to your plugins folder