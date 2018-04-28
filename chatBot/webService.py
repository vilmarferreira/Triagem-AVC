from flask import Flask, jsonify, request
import peewee
import json

banco = peewee.SqliteDatabase('banco.db')


class Postagem(peewee.Model):
    nome = peewee.CharField()
    lat = peewee.TextField()
    longi = peewee.TextField()

    def to_dict(self):
        return {'id': self.id, 'nome': self.nome, 'lat': self.lat, 'longi': self.longi}

    class Meta:
        database = banco


try:
    banco.create_table(Postagem)
except Exception as e:
    pass

app = Flask(__name__)


# GET /postagens/
@app.route('/postagens')
def postagens():
    return jsonify([postagem.to_dict() for postagem in Postagem.select()])


# GET /postagens/1
@app.route('/postagens/<int:id_postagem>')
def postagem(id_postagem):
    try:
        postagem = Postagem.get(id=id_postagem)
        return jsonify(postagem.to_dict())
    except Postagem.DoesNotExist:
        return jsonify({'status': 404, 'mensagem': 'Postagem não encontrada'})


# POST /postagens/
@app.route('/postagens', methods=['POST'])
def nova_postagem():
    dados = request.get_json()
    postagem = Postagem()
    # postagem.conteudo = dados['conteudo']
    # postagem.titulo = dados['titulo']
    data = json.loads(request.data.decode())

    postagem.titulo=data['titulo']

    postagem.save()

    return jsonify({'status': 200, 'mensagem': 'Postagem salva com sucesso!'})


# PUT/PATCH /postagens/1
@app.route('/postagens/id_postagem', methods=['PUT', 'PATCH'])
def editar_postagem(id_postagem):
    dados = request.json

    try:
        postagem = Postagem.get(id=id_postagem)
    except Postagem.DoesNotExist as e:
        return jsonify({'status': 404, 'mensagem': 'Postagem não encontrada'})

    postagem.titulo = dados['titulo']
    postagem.conteudo = dados['conteudo']
    postagem.save()

    return jsonify({'status': 200, 'mensagem': 'Postagem salva com sucesso'})


# DELETE /postagens/1
@app.route('/postagens/<int:id_postagem>', methods=['DELETE'])
def apagar_postagem(id_postagem):
    try:
        postagem = Postagem.get(id=id_postagem)
        postagem.delete_instance()

        return jsonify({'status': 200, 'mensagem': 'Postagem excluída com sucesso'})

    except Postagem.DoesNotExist:
        return jsonify({'status': 404, 'mensagem': 'Postagem não encontrada'})


if __name__ == '__main__':
    app.run(debug=True)