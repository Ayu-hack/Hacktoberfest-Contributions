import hashlib
import os

class VersionControlSystem:
    def __init__(self):
        # Initialize a dictionary to store versions of files
        self.versions = {}

    def hash_file(self, filepath):
        """Generate SHA-256 hash for a given file."""
        try:
            hasher = hashlib.sha256()  # Create a SHA-256 hash object
            with open(filepath, 'rb') as f:
                # Read the file in chunks to handle large files
                while chunk := f.read(8192):
                    hasher.update(chunk)  # Update the hash with the file's content
            return hasher.hexdigest()  # Return the hexadecimal digest of the hash
        except FileNotFoundError:
            print(f"File not found: {filepath}")  # Handle case where file does not exist
            return None

    def add_version(self, filepath):
        """Adds a new version of the file if the content has changed."""
        file_hash = self.hash_file(filepath)  # Generate the file's hash
        if file_hash:
            # Check if the file is new or if the content has changed
            if filepath not in self.versions or self.versions[filepath][-1] != file_hash:
                if filepath not in self.versions:
                    self.versions[filepath] = []  # Initialize an entry for new files
                self.versions[filepath].append(file_hash)  # Add the new version
                print(f"New version added for file: {filepath}")
            else:
                print(f"No changes detected for file: {filepath}")  # Content is unchanged
        else:
            print(f"Skipping file: {filepath} (File not found or unreadable)")  # Handle errors

    def display_versions(self, filepath):
        """Display all stored versions of the given file."""
        if filepath in self.versions:
            print(f"Versions of {filepath}:")
            for version, file_hash in enumerate(self.versions[filepath], 1):
                print(f"Version {version}: {file_hash}")  # Show each version's hash
        else:
            print(f"No versions found for file: {filepath}")  # No versions exist for this file

# Test Cases
if __name__ == "__main__":
    vcs = VersionControlSystem()  # Create an instance of the VersionControlSystem
    vcs.add_version("file1.txt")  # Add a version for file1.txt
    vcs.add_version("file2.txt")  # Add a version for file2.txt
    vcs.display_versions("file1.txt")  # Display versions of file1.txt

    # Simulating file content changes
    vcs.add_version("file1.txt")  # Add a new version if file1.txt's content has changed
    vcs.add_version("file2.txt")  # Add a new version if file2.txt's content has changed

    # Edge Case: Attempting to add a version for a non-existent file
    vcs.add_version("nonexistent.txt")  # Should print a file not found message
