# Secure oauth2 server with wss error messaging


## Aim

Spring boot 3.0.4 based oauth2 authorization server with simple functionalities on top.
<br/> Based on preplanned steps to practice and improve spring boot skills.

## Using app

1. Save content in `~/work/bnauth/`
### Localhost osx tests

Note: To test on other unix systems, change [local.sh](local.sh) runApps() `osascript` commands to ones matching your system.
<br/> Eventually run script to install apps and then run them manually using steps 4 and 5 from below.
1. Run [local.sh](local.sh) script. 
<br/>For example, `sh local.sh -p keystorePasword`.
   <br/>It will start postgres container, and spring boot app. It will also start
   react app in browser. If it doesn't, open [http://localhost:3000](http://localhost:3000) manually.

or
1. In project root execute `./gradlew clean build` 
2. In project root execute `./gradlew pgRun`
3. Execute `npm i` in [ws-client](ws-client) folder
4. Start spring app with your IDE or `./gradlew bootRun`
5. Start react app with `npm start` in [ws-client](ws-client) folder


### Docker tests
1. Run [docker.sh](docker.sh) script in root folder.
