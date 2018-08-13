
Spring boot app read and process file data.

Run at default interval of 10 seconds using Quartz Schedule, can be customized when to run app.

## Build
```
graldew build
```

## Test
```
graldew test
```
- JaCoCo Code Coverage report
```
gradlew jacocoTestReport

# output ${buildDir}/jacocoHtml/index.html
```

## Run
After executed build can execute the jar file in ${build}/libs/(app).jar

Properties can be passed how parameter on start app using -D
```
app.job.interval=10 #seconds
app.data.directory.in=${user.home}/data/in
app.data.directory.out=${user.home}/data/out
``` 
