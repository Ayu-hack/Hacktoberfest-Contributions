import sqlite3
import sys
import os

DATABASE = 'todo.db'

class TodoManager:
    def __init__(self):
        self.conn = sqlite3.connect(DATABASE)
        self.create_table()

    def create_table(self):
        with self.conn:
            self.conn.execute('''
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user TEXT NOT NULL,
                    task TEXT NOT NULL,
                    status TEXT NOT NULL CHECK (status IN ('pending', 'completed'))
                )
            ''')

    def add_task(self, user, task):
        with self.conn:
            self.conn.execute('INSERT INTO tasks (user, task, status) VALUES (?, ?, ?)', (user, task, 'pending'))
            print(f"Task added for user {user}: {task}")

    def remove_task(self, task_id):
        with self.conn:
            self.conn.execute('DELETE FROM tasks WHERE id = ?', (task_id,))
            print(f"Task {task_id} removed.")

    def complete_task(self, task_id):
        with self.conn:
            self.conn.execute('UPDATE tasks SET status = ? WHERE id = ?', ('completed', task_id))
            print(f"Task {task_id} marked as completed.")

    def list_tasks(self, user):
        cursor = self.conn.execute('SELECT id, task, status FROM tasks WHERE user = ?', (user,))
        tasks = cursor.fetchall()
        if tasks:
            print(f"\nTasks for user {user}:")
            for task in tasks:
                print(f"[{task[2]}] {task[0]}: {task[1]}")
        else:
            print(f"No tasks found for user {user}.")

def main():
    manager = TodoManager()
    
    if len(sys.argv) < 2:
        print("Usage: python todo_manager.py <username> [command] [args]")
        print("Commands: add <task>, remove <task_id>, complete <task_id>, list")
        sys.exit(1)

    user = sys.argv[1]
    command = sys.argv[2] if len(sys.argv) > 2 else None

    if command == 'add' and len(sys.argv) == 4:
        manager.add_task(user, sys.argv[3])
    elif command == 'remove' and len(sys.argv) == 4:
        manager.remove_task(int(sys.argv[3]))
    elif command == 'complete' and len(sys.argv) == 4:
        manager.complete_task(int(sys.argv[3]))
    elif command == 'list':
        manager.list_tasks(user)
    else:
        print("Invalid command or arguments.")

if __name__ == "__main__":
    main()
