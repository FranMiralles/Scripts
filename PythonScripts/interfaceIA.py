import tkinter as tk
from tkinter import PhotoImage
from PIL import Image
raiz = tk.Tk()

raiz.title("Página Inicio")
backImage = tk.PhotoImage(file='/home/fran/Escritorio/GIT/españa.png')
imgWidth = backImage.width()
imgHeight = backImage.height()
backLabel = tk.Label(raiz, image=backImage)
backLabel.place(x=0, y=0, relwidth=1, relheight=1)

raiz.geometry(f"{imgWidth}x{imgHeight}")

raiz.mainloop()