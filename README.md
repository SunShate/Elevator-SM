# OmSTU intelligent systems lab 3
* Elevators finite state machine
Program contains controller for filling the elevators queue with events

Two elevators, each have a SM.

## Dependencies

- java 11
- maven

## Build

```shell
mvn package
```

## Prepare

Before running the program you need to write an elevators events in the input.txt file in format:

- in one row
```text
(5, 2), ...,  (1, 6)
```
- multiple rows
```text
(5, 2)
 ...
(1, 6)
```
- both with any length, it'll compute events by each row
```text
(5, 2), ...,  (1, 6)
 ..., ..., (9, 2)
(1, 6), ...
```

## Run

Program takes three arguments:
1) number of floors in the building
2) first elevator start floor
3) second elevator start floor

```shell
java -jar ./target/elevator-state-machine-1.0-SNAPSHOT.jar './input.txt'
```

Result of a program will be written in output.txt file in the project directory

## Result
The sequence of elevator commands and the number of movements between floors for each event performed.