import random

def rock_paper_scissors():
    choices = ['rock', 'paper', 'scissors']
    computer_choice = random.choice(choices)

    print("Welcome to Rock, Paper, Scissors!")
    user_choice = input('Enter your choice (rock, paper, scissors): ').lower()

    if user_choice not in choices:
        print("Invalid choice, please try again.")

    print(f"Computer chose: {computer_choice}")

    if user_choice == computer_choice:
        print("Its's a tie!")
    elif (user_choice == "rock" and computer_choice == "scissors") or \
         (user_choice == "paper" and computer_choice == "rock") or \
         (user_choice == "scissors" and computer_choice == "paper"):
        print("You win!")

    else:
        print("You lose!")

rock_paper_scissors()

