import subprocess

print("{:<30} | {:<}".format("Wi-Fi Name", "Password"))
print("__________________________________________________")


wifi_profiles = subprocess.check_output(['netsh', 'wlan', 'show', 'profiles']).decode('utf-8', errors="backslashreplace")
profiles = [line.split(":")[1].strip() for line in wifi_profiles.split('\n') if "All User Profile" in line]

for profile in profiles:
    try:
        # Get the password for the Wi-Fi profile
        result = subprocess.check_output(['netsh', 'wlan', 'show', 'profile', profile, 'key=clear']).decode('utf-8', errors="backslashreplace")
        password_lines = [line.split(":")[1].strip() for line in result.split('\n') if "Key Content" in line]

        
        if password_lines:
            print("{:<30} | {:<}".format(profile, password_lines[0]))
        else:
            print("{:<30} | {:<}".format(profile, "Password not found"))

    except subprocess.CalledProcessError:
        print(f"Failed to retrieve password for {profile}")

print("\nWi-Fi passwords retrieved successfully!")
