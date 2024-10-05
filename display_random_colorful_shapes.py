import turtle
import random

# Setup the turtle screen
screen = turtle.Screen()
screen.bgcolor("black")

# Create a turtle
pen = turtle.Turtle()
pen.speed(0)  # Fastest speed
pen.width(2)

# List of colors to choose from
colors = ["red", "green", "blue", "yellow", "purple", "orange", "pink", "white"]

# Function to draw a shape
def draw_shape(sides, length):
    angle = 360 / sides
    for _ in range(sides):
        pen.forward(length)
        pen.right(angle)

# Random pattern drawing
for _ in range(100):
    sides = random.randint(3, 10)  # Random number of sides (3 for triangle to 10 for decagon)
    length = random.randint(50, 100)  # Random side length
    pen.color(random.choice(colors))  # Random color
    pen.penup()
    pen.goto(random.randint(-300, 300), random.randint(-300, 300))  # Random position on screen
    pen.pendown()
    draw_shape(sides, length)

# Hide the turtle and finish
pen.hideturtle()

# Keeps the window open
screen.mainloop()
