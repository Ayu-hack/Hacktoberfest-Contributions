# Tic Tac Toe Game

## Overview

This is a simple yet engaging Tic Tac Toe game implemented in Python using the Tkinter library. The game allows players to choose between playing against each other or challenging a computer opponent that employs a minimax algorithm for optimal gameplay. The project serves as a great introduction to GUI programming in Python and basic AI concepts.

## Features

- **Two Game Modes**:
  - **Human vs Human**: Two players can take turns to play against each other.
  - **Human vs Computer**: One player can challenge a computer that uses the minimax algorithm to make optimal moves.

- **Graphical User Interface**: 
  - An intuitive and user-friendly interface built with Tkinter that enhances user experience.

- **Responsive Design**: 
  - The game board adjusts dynamically to fit different window sizes, ensuring a smooth gameplay experience.

- **Game Over Notifications**: 
  - Alerts to notify players when a player wins or when the game ends in a draw.

## How to Play

1. **Launch the Game**: When the game starts, you will be presented with two options for game modes.
2. **Choose Your Game Mode**:
   - **Play with Human**: Each player will take turns clicking on the grid to place their marks (X or O).
   - **Play with Computer**: The human player will play as X, and the computer will automatically play as O.
3. **Objective**: The first player to align three of their marks horizontally, vertically, or diagonally wins the game.
4. **Draw Condition**: If all nine squares are filled without a winner, the game will declare a draw.
5. **Game Over Notifications**: The game will show a message box indicating the winner or if the game has ended in a draw.

## Code Overview

The main class in the code is `TicTacToe`, which encompasses the game logic and user interface components.

### Key Components:

- **Initialization (`__init__()`)**: Sets up the main window, initializes game variables, and presents mode selection.
- **Game Mode Selection (`choose_mode()`)**: Displays options for choosing between playing with another human or the computer.
- **Board Creation (`create_board()`)**: Generates a 3x3 grid for the game using Tkinter buttons.
- **Making Moves (`make_move(row, col)`)**: Updates the board with the current player's mark and checks for win/draw conditions.
- **Computer Move (`computer_move()`)**: Executes the computer's turn using the minimax algorithm to select the best possible move.
- **Minimax Algorithm (`minimax(depth, is_max)`)**: Evaluates possible game states recursively to determine the optimal move for the computer.
- **Game Status Check (`check_winner(player)`)**: Checks if the given player has won the game based on the current board state.

### Important Methods:

```python
def __init__(self):  # Initializes the game window and sets up variables
def choose_mode(self):  # Displays the mode selection interface
def create_board(self):  # Creates the game board layout
def make_move(self, row, col):  # Updates the board based on player input
def computer_move(self):  # Executes the computer's turn
def minimax(self, depth, is_max):  # Implements the minimax algorithm
def check_winner(self, player):  # Checks for a winning condition
