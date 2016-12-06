# spark_svm_scala
This is use the spark machine library to run SVM algorithm

# create a new repository from scratch
 -  create a new repository from github such as : https://github.com/bedsDev/spark_svm_scala.git
 -  go to the project folder: E:\workspaces\scala\svm_ex
 ```bash
 	git init
 	git add .
 	git commit -m "svm spark in scala"

 	git remote add origin  https://github.com/bedsDev/spark_svm_scala.git
 	git config --global user.email "shaopeng.wu@beds.ac.uk"
 	git config --global user.name "shaopeng wu"
 	git pull --allow-unrelated-histories https://github.com/bedsDev/spark_svm_scala.git master

    git add .
    git commit -m "update remote repository"

    git push -u origin master


 ```
 

# Command lines to build and sumbit spark packages

```bash
   sbt package
   \softwares\spark-2.0.0-bin-hadoop2.7\bin\spark-submit --class org.ccgv.nlp.SVMExample --master local[4] target\scala-2.11\svm_2.11-1.0.jar

 ```
## This supposes the spark is downloaded and put into folder: \softwares\spark-2.0.0-bin-hadoop2.7


 
## Links:
 1. [git hub markdown reference link](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

 1. [repository setting remote](http://kbroman.org/github_tutorial/pages/init.html)

 1. [repository first time](http://kbroman.org/github_tutorial/pages/first_time.html)


 ## issuse: target was not ignored because .gitignore was added after the initial, so that commands below used to solve the problems.

 ```bash
 	git rm -rf --cached .
 	git add .
 ```

## winutils.exe errors for the 
 1. download package from [hadoopn windows](https://codeload.github.com/sardetushar/hadooponwindows/zip/master)

 1. extract the file to put folder E:\softwares\spark-2.0.0-bin-hadoop2.7\hadooponwindows-master

 1. add above path to env as HADOOP_HOME

 1. append %HADOOP_HOME% to %PATH%
