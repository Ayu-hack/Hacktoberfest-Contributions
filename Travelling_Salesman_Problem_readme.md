# Traveling Salesman Problem (TSP)

## Overview

This project implements a brute-force solution to the Traveling Salesman Problem (TSP) using Python. The TSP is a classic optimization problem that seeks to find the shortest possible route for a salesman to visit a set of cities and return to the origin city. This implementation allows users to input a distance matrix and calculates the shortest possible route.

## Features

- **User Input for Distance Matrix**: Users can enter distances between cities, including the option to specify 'inf' for no direct path.
- **Brute-force Calculation**: Utilizes itertools to generate all permutations of city paths to determine the shortest route.
- **Result Display**: Outputs the shortest path and its corresponding distance.

## How It Works

1. **Input Distance Matrix**: The program prompts the user to enter the distances between each pair of cities in a matrix format.
2. **Brute-force Path Calculation**: It calculates all possible paths and determines the one with the minimal distance.
3. **Output**: Displays the optimal path along with the minimum distance.

### Key Functions

- **get_distance_matrix(num_cities)**: 
  - Prompts the user to enter the distance matrix and constructs a list of lists (2D array) representing distances between cities. It handles invalid inputs and ensures that distances are non-negative.

- **calculate_total_distance(distance_matrix, path)**: 
  - Calculates the total distance for a given path by summing the distances between consecutive cities, including returning to the starting city.

- **traveling_salesman_brute_force(distance_matrix)**: 
  - Generates all possible paths using permutations, calculates their total distances, and identifies the path with the minimum distance.

- **main()**: 
  - The entry point of the program where user input is handled and results are displayed.

## Requirements

- Python 3.x

