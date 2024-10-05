import tkinter as tk
from tkinter import messagebox
import random

class TicTacToe:
    def __init__(self):
        self.window = tk.Tk()
        self.window.title("Tic Tac Toe")
        self.window.geometry("450x500")
        
        self.scores = {"X": 0, "O": 0}
        self.current_player = random.choice(["X", "O"])
        self.board = [["" for _ in range(3)] for _ in range(3)]
        
        self.mode = tk.StringVar(value="Medium")  # Default difficulty
        self.create_mode_selection()

    def create_mode_selection(self):
        self.clear_window()
        
        label = tk.Label(self.window, text="Choose Game Mode", font=("Helvetica", 20))
        label.pack(pady=20)
        
        difficulty_label = tk.Label(self.window, text="Choose Difficulty", font=("Helvetica", 16))
        difficulty_label.pack(pady=10)
        
        for level in ["Easy", "Medium", "Hard"]:
            tk.Radiobutton(self.window, text=level, variable=self.mode, value=level, font=("Helvetica", 14)).pack(pady=5)
        
        human_button = tk.Button(self.window, text="Play with Human", font=("Helvetica", 16), command=self.start_human_mode)
        human_button.pack(pady=10)
        
        computer_button = tk.Button(self.window, text="Play with Computer", font=("Helvetica", 16), command=self.start_computer_mode)
        computer_button.pack(pady=10)

    def clear_window(self):
        for widget in self.window.winfo_children():
            widget.destroy()

    def create_scoreboard(self):
        self.score_label = tk.Label(self.window, text=f"X: {self.scores['X']}  O: {self.scores['O']}", font=("Helvetica", 16))
        self.score_label.grid(row=0, column=0, columnspan=3, pady=10)

    def start_human_mode(self):
        self.create_board()

    def start_computer_mode(self):
        self.mode.set("Computer")  # Set mode to Computer
        self.create_board()

    def create_board(self):
        self.clear_window()
        self.create_scoreboard()

        self.buttons = []
        for i in range(3):
            row = []
            for j in range(3):
                button = tk.Button(self.window, text="", width=10, height=5, font=("Helvetica", 18),
                                   bg="lightblue", activebackground="lightgreen",
                                   command=lambda i=i, j=j: self.make_move(i, j))
                button.grid(row=i+1, column=j)
                row.append(button)
            self.buttons.append(row)
        
        reset_button = tk.Button(self.window, text="Reset", command=self.reset_game, font=("Helvetica", 14))
        reset_button.grid(row=4, column=0, columnspan=3, pady=10)

        restart_button = tk.Button(self.window, text="Restart", command=self.restart_game, font=("Helvetica", 14))
        restart_button.grid(row=5, column=0, columnspan=3, pady=10)
        
        if self.current_player == "O" and self.mode.get() == "Computer":
            self.computer_move()

    def make_move(self, row, col):
        if self.board[row][col] == "":
            self.board[row][col] = self.current_player
            self.buttons[row][col].config(text=self.current_player)
            if self.check_winner(self.current_player):
                self.scores[self.current_player] += 1
                self.score_label.config(text=f"X: {self.scores['X']}  O: {self.scores['O']}")
                self.highlight_winner(self.current_player)
                messagebox.showinfo("Game Over", f"Player {self.current_player} wins!")
                self.restart_game()
            elif self.is_board_full():
                messagebox.showinfo("Game Over", "It's a draw!")
                self.restart_game()
            else:
                if self.mode.get() == "Human":
                    self.current_player = "O" if self.current_player == "X" else "X"
                else:
                    self.current_player = "O"
                    self.computer_move()

    def computer_move(self):
        move = self.find_best_move() if self.mode.get() == "Hard" else self.random_move()
        if move:
            row, col = move
            self.board[row][col] = self.current_player
            self.buttons[row][col].config(text=self.current_player)
            if self.check_winner(self.current_player):
                self.scores["O"] += 1
                self.score_label.config(text=f"X: {self.scores['X']}  O: {self.scores['O']}")
                self.highlight_winner(self.current_player)
                messagebox.showinfo("Game Over", "Computer wins!")
                self.restart_game()
            elif self.is_board_full():
                messagebox.showinfo("Game Over", "It's a draw!")
                self.restart_game()
            else:
                self.current_player = "X"

    def random_move(self):
        available_moves = [(i, j) for i in range(3) for j in range(3) if self.board[i][j] == ""]
        return random.choice(available_moves) if available_moves else None

    def find_best_move(self):
        best_val = -1000
        best_move = (-1, -1)
        
        for i in range(3):
            for j in range(3):
                if self.board[i][j] == "":
                    self.board[i][j] = self.current_player
                    move_val = self.minimax(0, False)
                    self.board[i][j] = ""
                    if move_val > best_val:
                        best_move = (i, j)
                        best_val = move_val
        return best_move

    def minimax(self, depth, is_max):
        score = self.evaluate()

        if score == 10:
            return score - depth
        if score == -10:
            return score + depth
        if self.is_board_full():
            return 0

        if is_max:
            best = -1000
            for i in range(3):
                for j in range(3):
                    if self.board[i][j] == "":
                        self.board[i][j] = self.current_player
                        best = max(best, self.minimax(depth + 1, not is_max))
                        self.board[i][j] = ""
            return best
        else:
            best = 1000
            opponent = "O" if self.current_player == "X" else "X"
            for i in range(3):
                for j in range(3):
                    if self.board[i][j] == "":
                        self.board[i][j] = opponent
                        best = min(best, self.minimax(depth + 1, not is_max))
                        self.board[i][j] = ""
            return best

    def evaluate(self):
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] != "":
                return 10 if self.board[i][0] == self.current_player else -10
            if self.board[0][i] == self.board[1][i] == self.board[2][i] != "":
                return 10 if self.board[0][i] == self.current_player else -10
        if self.board[0][0] == self.board[1][1] == self.board[2][2] != "":
            return 10 if self.board[0][0] == self.current_player else -10
        if self.board[0][2] == self.board[1][1] == self.board[2][0] != "":
            return 10 if self.board[0][2] == self.current_player else -10
        return 0

    def is_board_full(self):
        return all(self.board[i][j] != "" for i in range(3) for j in range(3))

    def check_winner(self, player):
        for i in range(3):
            if self.board[i][0] == self.board[i][1] == self.board[i][2] == player:
                return True
            if self.board[0][i] == self.board[1][i] == self.board[2][i] == player:
                return True
        if self.board[0][0] == self.board[1][1] == self.board[2][2] == player:
            return True
        if self.board[0][2] == self.board[1][1] == self.board[2][0] == player:
            return True
        return False

    def highlight_winner(self, player):
        # Implement highlighting the winning combination if needed
        pass

    def reset_game(self):
        self.board = [["" for _ in range(3)] for _ in range(3)]
        self.current_player = random.choice(["X", "O"])
        self.create_board()

    def restart_game(self):
        self.scores = {"X": 0, "O": 0}
        self.reset_game()

if __name__ == "__main__":
    game = TicTacToe()
    game.window.mainloop()
