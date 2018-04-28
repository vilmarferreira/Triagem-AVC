# coding: utf-8
import requests
import traceback
import json
from flask import Flask, request


token = "EAALN1vcHyMkBANBCw72LjjZCNz7rLljFZCxebGiVr1ZCwnoZA2SUvkvcHtsmG75qZBiNLm7mS1yAimGC6RvwtTZAgIKMrAZCnWFc1lnCfZApcj0blIglj2DJQdkCVsX91o6RfocyEFxgDS8KnBf6HC9u4ZBx6Lk61XUF2OAAQf264h32z847ZChBD8"
app = Flask(__name__)
@app.route('/', methods=['GET', 'POST'])

def webhook():
    if request.method == 'POST':
        arq = open("arquivo.txt", "r+")
        try:
            data = json.loads(request.data.decode())
            text = data['entry'][0]['messaging'][0]['message']['text']
            sender = data['entry'][0]['messaging'][0]['sender']['id']

            num_lines = 0
            for line in arq:
                print(line)
                num_lines += 1
                print(num_lines)

            if text == "Ajuda":
                payload = {'recipient': {'id': sender}, 'message': {'text': "Você esta com dor de cabeca? "}}
                arq.write("Você está com dor de cabeça? \n")
            else:
                lines = []
                for line in arq:
                    print(line)
                    lines.append(num_lines)
                print(lines)
                pergunta = lines[0]
                if pergunta == "Você esta com dor de cabeca?":
                    pergunta = "Você possui mais de 65 anos?"
                elif pergunta == "Você possui mais de 65 anos?":
                    pergunta = "Você esta com dormencia?"
                elif pergunta == "Você esta com dormencia?":
                    countNo = 0
                    countYes = 0
                    for line in arq:
                        if line == "Nao":
                            countNo += 1
                        elif line == "Sim":
                            countYes += 1
                    if(countYes >= 2):
                        pergunta = "Você terá prioridade no hospital mais próximo"
                arq.write(pergunta + " \n")
                payload = {'recipient': {'id': sender}, 'message': {'text': pergunta}}

            r = requests.post('https://graph.facebook.com/v2.6/me/messages/?access_token=' + token, json=payload)
            arq.close()
        except Exception as e:
            print(traceback.format_exc())
    elif request.method == 'GET': # Para a verificação inicial (configuração facebook)
        if request.args.get('hub.verify_token') == '168486848618416546116':
            return request.args.get('hub.challenge')
        return "Token Inválido"
    return "Nada a retornar"

if __name__ == '__main__':
    app.run(debug=True)

