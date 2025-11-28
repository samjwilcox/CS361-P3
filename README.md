# Project 3: Turing Machine Simulator

* Author: Tyler Fernandez, Sam Wilcox
* Class: CS361 Section 1
* Semester: Fall 2025

## Overview

This program simulates a bi-infinite turing machine that
parses an encoding of a turing machine together with an input string,
then simulates the instance of a turing machine with the input.

## Reflection


This project mostly went smoothly, the only issue being file5.txt. When I added a check
for output length and the sum of output tape, I was getting values that were
significantly lower than the acutal expected value. Additionally, when running the
program on file5, the program would run for longer than 5 minutes, which wouldn't receive
a passing mark. To fix this, I implemented a step by step system to run until it halts.  
After this project, I have a good understanding of turing machines after this project.
Some techniques that I used to make our code easy to debug and modify include using 
even spacing and comments. For this turing machine implementation, I made sure to draw
out the turing machine and what the tape looked like at all times.

If I could change something about my design process, I would give more time to identify
edge cases and testing. My partner and I got caught up in a lot of other work and Thanksgiving Break, which
made it hard to find time to thoroughly test the program. If I could go back in time,
I would tell myself to start earlier on this project, as it would've allowed more time
for testing. 


## Compiling and Using

This program requires user input through a test, text file.

To compile the code, clone the repository locally then run the following commands:
```
javac TMSimulator.java
```
Then once compiled, run a test file.
```
java TMSimulator.java test-files/file[0-9].txt
```

## Sources used

Used class notes and slides