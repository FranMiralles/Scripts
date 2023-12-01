#import openai as ai


#ai.api_key = 
#res = ai.Completion.create(engine="gpt-3.5-turbo", prompt="¿Qué es chatGPT?", max_tokens=2048)

#print(res.choices[0].text)

from PIL import Image
import pytesseract as py

ruta_imagen = "/home/fran/Escritorio/GIT/Scripts/PythonScripts/img.jpg"
imagen_abierta = Image.open(ruta_imagen)
print(py.image_to_string(imagen_abierta))
