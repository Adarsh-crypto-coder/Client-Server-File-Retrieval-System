[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/bn26Dqcl)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=13898345&assignment_repo_type=AssignmentRepo)
## CSC435 Programming Assignment 3 (Winter 2024)
**Jarvis College of Computing and Digital Media - DePaul University**

**Student**: Adarsh Purushothama Reddy (apurusho@depaul.edu)  
**Solution programming language**: JAVA 

### Requirements

If you are implementing your solution in C++ you will need to have GCC 12.x and CMake 3.22.x installed on your system. On Ubuntu 22.04 you can install GCC and set it as default compiler using the following commands:

```
sudo apt install g++-12 gcc-12 cmake
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 110
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-12 120
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-11 110
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-12 120
```

If you are implementing your solution in Java you will need to have Java 1.7.x and Maven 3.6.x installed on your systems. On Ubuntu 22.04 you can install Java and Maven using the following commands:

```
sudo apt install openjdk-17-jdk maven

```

### Setup

There are 5 datasets (Dataset1, Dataset2, Dataset3, Dataset4, Dataset5) that you need to use to evaluate your solution. Before you can evaluate your solution you need to download the datasets. You can download the datasets from the following link:

https://depauledu-my.sharepoint.com/:f:/g/personal/aorhean_depaul_edu/EgmxmSiWjpVMi8r6QHovyYIB-XWjqOmQwuINCd9N_Ppnug?e=TLBF4V

After you finished downloading the datasets copy them to the dataset directory (create the directory if it does not exist). Here is an example on how you can copy Dataset1 to the remote machine and how to unzip the dataset:

```
remote-computer$ mkdir datasets
local-computer$ scp Dataset1.zip cc@<remote-ip>:<path-to-repo>/datasets/.
remote-computer$ cd <path-to-repo>/datasets
remote-computer$ unzip Dataset1.zip
```

### Java solution
#### How to build/compile

To build the Java solution use the following commands:
```
cd app-java
mvn compile
mvn package
```

#### How to run application

To run the Java server (after you build the project) use the following command:
```
To run the command you have to cd to the directory C:\Users\Adhuuuu\Downloads\csc435-pa3-Adarsh-crypto-coder\app-java\src\main\java> or have you have to be in the \java directory

java  csc435.app.FileRetrievalServer <numPorts> <portStart> <maxConnections>

Example: java  csc435.app.FileRetrievalServer 1 2000 10

This will start the server with one port 2000. You can specify the as many ports as you want
```

To run the Java client (after you build the project) use the following command:
```
To run the command you have to cd to the directory C:\Users\Adhuuuu\Downloads\csc435-pa3-Adarsh-crypto-coder\app-java\src\main\java> or have you have to be in the \java directory

java csc435.app.FileRetrievalClient
```

#### Example (2 clients and 1 server)

**Step 1:** start the server:

Server
```
Example: java  csc435.app.FileRetrievalServer 2 2000 10
>
```

**Step 2:** start the clients and connect them to the server:

Client 1
```
java csc435.app.FileRetrievalClient
> connect 127.0.0.1 2000
Connection successful!
```

Client 2
```
java csc435.app.FileRetrievalClient
> connect 127.0.0.1 2001
Connection successful!
```

**Step 3:** list the connected clients on the server:

Server
```
> list
client1: 127.0.0.1 2000
client2: 127.0.0.1 2001
```

**Step 4:** index files from the clients:

Client 1
```
> index ../datasets/Dataset1/folder1
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder3
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder5
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder7
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder9
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder11
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder13
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder15
Completed indexing in 1.386 seconds
```

Client 2
```
> index ../datasets/Dataset1/folder2
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder4
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder6
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder8
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder10
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder12
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder14
Completed indexing in 1.386 seconds
> index ../datasets/Dataset1/folder16
Completed indexing in 1.386 seconds
```

**Step 5:** search files from the clients:

Client 1
```
> search Worms
Search completed in 2.8 seconds
Search results (top 10):
* client2:folder6/document200.txt 11
* client2:folder14/document417.txt 4
* client2:folder6/document424.txt 4
* client1:folder11/document79.txt 1
* client2:folder12/document316.txt 1
* client1:folder13/document272.txt 1
* client1:folder13/document38.txt 1
* client1:folder15/document351.txt 1
* client1:folder1/document260.txt 1
* client2:folder4/document101.txt 1
```

Client 2
```
> search distortion AND adaptation
Search completed in 3.27 seconds
Search results (top 10):
* client2:folder6/document200.txt 57
* client1:folder7/document476.txt 5
* client1:folder13/document38.txt 4
* client2:folder6/document408.txt 3
* client1:folder7/document298.txt 3
* client2:folder10/document107.txt 2
* client2:folder10/document206.txt 2
* client2:folder10/document27.txt 2
* client2:folder14/document145.txt 2
* client1:folder15/document351.txt 2
> quit
```

**Step 6:** close and disconnect the clients:

Client 1
```
> quit
```

Client 2
```
> quit
```

**Step 7:** close the server:

Server
```
> quit
```
