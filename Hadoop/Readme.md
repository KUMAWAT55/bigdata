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
###### (1) For setting up PATH and JAVA_HOME variables, add the following commands to file(~/.bashrc) .

          export JAVA_HOME=/usr/local/jdk-9.0.4
          export PATH=$PATH:$JAVA_HOME/bin 
###### (2) You can set Hadoop environment variables by appending the following commands to (~/.bashrc) file. 

          export HADOOP_HOME=/usr/local/hadoop 
          export HADOOP_MAPRED_HOME=$HADOOP_HOME 
          export HADOOP_COMMON_HOME=$HADOOP_HOME 
          export HADOOP_HDFS_HOME=$HADOOP_HOME 
          export YARN_HOME=$HADOOP_HOME 
          export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native 
          export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin 
          export HADOOP_INSTALL=$HADOOP_HOME 
###### (3) Now apply all the changes into the current running system.
          $ source ~/.bashrc
###### (4) In the distribution, edit the file ((/usr/local/hadoop/etc/hadoop/hadoop-env.sh)) to define some parameters as follows:
###### set to the root of your Java installation
          export JAVA_HOME=/usr/local/jdk-9.0.4

Try the following command:
         
         $ hadoop
This will display the usage documentation for the hadoop script.
### Pseudo-Distributed Operation
###### (1) edit the file ((/usr/local/hadoop/etc/hadoop/core-site.xml)):

          <configuration>
                    <property>
                               <name>fs.defaultFS</name>
                               <value>hdfs://localhost:9000</value>
                    </property>
          </configuration>
          
###### (2) edit the file ((/usr/local/hadoop/etc/hadoop/hdfs-site.xml)):

          <configuration>
                     <property>
                              <name>dfs.replication</name>
                               <value>1</value>
                    </property>
          </configuration>
#####  Setup passphraseless ssh      
If you cannot ssh to localhost without a passphrase, execute the following commands:          
           
           $ ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
           $ cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
           $ chmod 0600 ~/.ssh/authorized_keys
