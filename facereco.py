import cv2
import os

def TakeImages():
    cam = cv2.VideoCapture(0)
    face_classifier = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
    sampleNum = 0
    
    while True:
        ret, img = cam.read()
        faces = face_classifier.detectMultiScale(img, 1.3, 5)

        for (x, y, w, h) in faces:
            cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)

        cv2.imshow('frame', img)
        key = cv2.waitKey(1) & 0xFF

        if key == ord('q'):  #press q key to exit 
            break
        elif key == ord('c'):  #press c to click 
            sampleNum += 1
            img_name = f"camimages/{sampleNum}.jpg"#folder path to sav image it should to be in same as your file in 
            cv2.imwrite(img_name, img)
            print(f"Image {sampleNum} captured and saved as {img_name}.")

    cam.release()
    cv2.destroyAllWindows()

TakeImages()
