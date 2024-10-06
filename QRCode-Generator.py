# Importing library
import qrcode

# Taking input from the user
print("")
data = input("Enter the text or URL you want to convert into a QR code: ")

# Encoding data using make() function
img = qrcode.make(data)

# Saving as an image file
filename = input("Enter the filename to save the QR code (without extension): ")
img.save(f'{filename}.png')

print(f"QR code saved as {filename}.png")


# This code is contributed by @sumedhx during Hacktoberfest 2024
