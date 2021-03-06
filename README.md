# Bus Routing System

Real world application of Dijkstra's Algorithm

## Problem definition

Bus routing system, described in these instructions, is a variation of the vehicle routing system. Unlike vehicle routing system, where all the stops are known, and routes should be determined, in this bus routing system only potential stops are given, and selection of stops and determining bus routes depends on desired locations and selection of each bus.

The goal of solving this problem is:

- to determine the set of stops to visit, and
- determine routes that lie along the chosen stops, so that the total travelled distance is minimized.

## Implementation of algorithm

To solve this problem, dijkstra's algorithm was designed. Java was choosen as a language of conduction. Program is constructed in modular way, so it can be roughly re-used. The main routing code is inside [Buses.java](https://github.com/Murthypsty0419/Bus-Routing-System/blob/main/JAVA%20Code/src/Buses.java) file.


### Generating routes for given instances

Overall operation of the routing algorithm is easily visible on the following images:

- 

<p align="center">
  <img width="502" height="655" src="https://user-images.githubusercontent.com/92665752/140971997-a75b74a5-d9fe-454c-9b49-217ee61f000f.png">
</p>

<p align="center">
  <img width="502" height="628" src="https://user-images.githubusercontent.com/92665752/140972036-9ef157a1-1caf-483e-a293-2901d9d879b0.png">
</p>
