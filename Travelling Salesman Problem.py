import itertools

def get_distance_matrix(num_cities):
    distance_matrix = []
    print("Enter the distances between the cities in a matrix form (row by row):")
    print("If there is no path between two cities, enter 'inf'.")

    for i in range(num_cities):
        while True:  
            row = input(f"Enter distances from city {i+1} to others: ").split()
            
            if len(row) != num_cities:
                print(f"Please enter {num_cities} distances for city {i+1}")
                continue  
            
            try:
                row = [float('inf') if x.lower() == 'inf' else float(x) for x in row]
                
                if any(x < 0 for x in row if x != float('inf')):  
                    print("Distances cannot be negative. Please enter the values again.")
                    continue  

                distance_matrix.append(row)
                break 
            except ValueError:
                print("Please enter valid numbers or 'inf'.")
    
    return distance_matrix

def calculate_total_distance(distance_matrix, path):
    total_distance = 0
    for i in range(len(path) - 1):
        total_distance += distance_matrix[path[i]][path[i + 1]]
    total_distance += distance_matrix[path[-1]][path[0]]  # Return to origin
    return total_distance

def traveling_salesman_brute_force(distance_matrix):
    num_cities = len(distance_matrix)
    cities = list(range(num_cities))
    all_possible_paths = itertools.permutations(cities[1:])
    
    min_distance = float('inf')
    best_path = None
    
    for path in all_possible_paths:
        path = [0] + list(path)
        current_distance = calculate_total_distance(distance_matrix, path)
        if current_distance < min_distance:
            min_distance = current_distance
            best_path = path
            
    return best_path, min_distance

def main():
    num_cities = int(input("Enter the number of cities: "))
    distance_matrix = get_distance_matrix(num_cities)
    
    best_path, min_distance = traveling_salesman_brute_force(distance_matrix)
    
    if min_distance == float('inf'):
        print("There is no possible path that visits all cities and returns to the origin.")
    else:
        print("The shortest path is: ")
        print(" -> ".join(map(lambda x: f"City {x+1}", best_path)))
        print(f"The minimum distance is: {min_distance}")

if __name__ == "__main__":
    main()
