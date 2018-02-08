### INSTALLATON HADOOP VERSION 3.0.0
Pre-requisites packages:
######  (1) Java SE Development Kit 9.0.4
http://download.oracle.com/otn-pub/java/jdk/9.0.4+11/c2514751926b4512b076cc82f959763f/jdk-9.0.4_linux-x64_bin.tar.gz
###### Extract
          $ tar -xvzf jdk-9.0.4_linux-x64_bin.tar.gz
          $ mv jdk-9.0.4 /usr/local/

###### (2) hadoop-3.0.0
http://www-eu.apache.org/dist/hadoop/common/hadoop-3.0.0/hadoop-3.0.0.tar.gz
###### Extract   

          $ tar -xvzf hadoop-3.0.0.tar.gz
          $ mv hadoop-3.0.0 /usr/local/hadoop

###### Requisites Software
          $ sudo apt-get install ssh
The SSH protocol (also referred to as Secure Shell) is a method for secure remote login from one computer to another
          
          $ sudo apt-get install pdsh
pdsh is a variant of the rsh(1) command. Unlike rsh(1), which runs commands on a single remote host, pdsh can run multiple remote commands in parallel

###### Configurations:
###### set to the root of your Java installation in bashrc file(command : $ vim ~/.bashrc)
          export JAVA_HOME=/usr/local/jdk-9.0.4
          export PATH=$PATH:$JAVA_HOME/bin 