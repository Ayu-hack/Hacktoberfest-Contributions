def fizz_buzz():
    result = []
    for i in range(1, 101):
        if i % 3 == 0 and i % 5 == 0:
            result.append("FizzBuzz")
        elif i % 3 == 0:
            result.append("Fizz")
        elif i % 5 == 0:
            result.append("Buzz")
        else:
            result.append(str(i))
    
    # Print the result in a formatted manner (10 items per line)
    for i in range(0, 100, 10):
        print(" | ".join(result[i:i+10]))

# Call the function
fizz_buzz()
