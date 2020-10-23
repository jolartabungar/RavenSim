# RavenSim
## Project Setup
### Prerequisites
- Node (I'm using [v12.19.0 LTS](https://nodejs.org/en/))
- Yarn ([v1.22.5](https://classic.yarnpkg.com/en/docs/install#windows-stable))
- Java/JRE

This repo contains both the client and server projects.
I used [VSCode](https://code.visualstudio.com/) for the client project since it is a yarn project.
I used [Eclipse](https://www.eclipse.org/downloads/) for the server project since it is a maven project.

### Cloning the Git repository
VSCode has an integrated terminal, which is where I run most of my commands.
Open VSCode and click on the Terminal tab, and select New Terminal option
Using the terminal, change your directory to where you would like to clone the repo.
You can create a RavenSim Folder somewhere then change directory to that
Then use the command :

`
git clone -v https://github.com/jolartabungar/RavenSim.git
`

Once you have cloned the repo, you can open it in VSCode using `File > Open Folder> Your Cloned Repo Folder`
You should be able to see the repo in the explorer pane now.

### Testing if Node, Yarn and Java are correctly installed
Once you have Node and Yarn installed (in that order), make sure they are in your path environment variable.
If they aren't you will need to add them, and potentially restart VSCode.

Run the following commands and confirm their versions:
- yarn -v or yarn --version
- node -v or node --version
- java -version

### Running Server Project
1. In Eclipse, go to `Help > Eclipse Marketplace`
2. Search for Spring, and install Spring Tools 4
3. Use `File > Import`
4. In the Import Window, select `Maven > Existing Maven Projects`, then click Next
5. In Root Directory, click Browse and select the server folder inside the repo folder
6. You should be able to see a pom.xml file in the Projects list.
7. Then click Finish
8. You should be able to see the server files in the explorer pane
9. Right click, `src/main/java` and click `Run as > Java Application`
10. You should see the server running in the Eclipse console window

### Running Client Project
1. In VSCode, change directory to the client folder inside the repo
2. Run the command in the terminal: `yarn install`, it will install the client package dependencies
3. After the dependencies have finished installing, run: `yarn start`
4. The client UI should open a new tab in your browser.

You should see something like this ![](https://github.com/jolartabungar/RavenSim/blob/master/images/example_setup.PNG)

