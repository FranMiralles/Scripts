import tkinter as tk
from tkinter import PhotoImage
from PIL import Image, ImageTk

# Instanciamos Tk
raiz = tk.Tk()
screenWidth = raiz.winfo_screenwidth()
screenHeight = raiz.winfo_screenheight()

raiz.title("Página Inicio")
raiz.geometry(f"{screenWidth}x{screenHeight}")
# Imagen risk fondo
im = Image.open('/home/fran/Escritorio/GIT/Scripts/PythonScripts/españa.png')
width = int(screenWidth * 0.6)
heigth = int(screenHeight * 0.8)
im = im.resize((width, heigth), Image.ANTIALIAS)
backSpain = ImageTk.PhotoImage(im)
backLabel = tk.Label(raiz, image=backSpain)
backLabel.place(x=0, y=0, relwidth=1, relheight=1)

framePrincipal = tk.Frame(raiz, bg = "red")

# Equipo de los jugadores
frame_izquierdo = tk.Frame(raiz, padx=10, pady=10, bg="blue")
frame_derecho = tk.Frame(raiz, padx=10, pady=10, bg="blue")

def on_button_click(text):
    print(text)
    
button_izquierdo = tk.Button(frame_izquierdo, text="Izquierda", command=lambda: on_button_click("Izquierdo"), width=10)
button_derecho = tk.Button(frame_derecho, text="Derecho", command=lambda: on_button_click("Derecho"), width=10)
button_izquierdo.pack(fill=tk.BOTH)
button_derecho.pack(fill=tk.BOTH)
frame_izquierdo.pack(side=tk.LEFT, fill=tk.BOTH)
frame_derecho.pack(side=tk.RIGHT, fill=tk.BOTH)


raiz.mainloop()