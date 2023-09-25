import tkinter as tk
from tkinter import PhotoImage
from PIL import Image, ImageTk

# Instancia de la ventana
raiz = tk.Tk()
frame = tk.Frame(raiz, padx=10, pady=10, bg="blue")
button = tk.Button(frame, text = "Izquierda")
button.pack(fill=tk.BOTH)
frame.pack(side=tk.LEFT, fill=tk.BOTH)


raiz.mainloop()