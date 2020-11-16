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

## Project Milestones
|               MileStone              |                                                      Description                                                     | Tentative Completion Date | Member Roles |
|:------------------------------------:|:--------------------------------------------------------------------------------------------------------------------:|:-------------------------:|:------------:|
|          Save A file locally         |                             Save the state of the circuit to a file stored on the server                             |        November 27        |              |
|          Load a file locally         |                                                Load circuit from file                                                |      Dec 11 - Dec 30 (Will be based on exam schedule)     |              |
|        GUI for saving/loading        |                                           Create the GUI for saving/loading                                          |       Same as above, this will be sometime during or after the reading week after exams                    |              |
| Google Drive integration exploration |                           Determine how to integrate file loading/saving with Google Drive                           |           Jan 8           |              |
|          User Collaboration          |                                Allow multiple users to modify a circuit synchornously                                |           Feb 12          |              |
|             Deploy Server            | All previous milestones run the server locally on a user machine; now server should be hosted using AWS, or Carleton |          March 12         |              |

## Useful Links!
Visualization of Operational Transformation that will possibly be used for synchronous collaboration.
https://operational-transformation.github.io/

The following are documents about Operational Transformation we can use when writing a report. I have not read through them yet. 
You will need to use your Carleton Credentials to see these.
https://ieeexplore-ieee-org.proxy.library.carleton.ca/document/4668339
https://dl-acm-org.proxy.library.carleton.ca/doi/pdf/10.1145/587078.587088
https://search-proquest-com.proxy.library.carleton.ca/docview/2258140325?accountid=9894&rfr_id=info%3Axri%2Fsid%3Aprimo

