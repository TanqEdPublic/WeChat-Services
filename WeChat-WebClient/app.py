from flask import Flask, url_for, render_template, request, json
import requests

app = Flask(__name__)


@app.route('/')
def index():
    indexCss = url_for("static", filename="css/index.css")
    loginCss = url_for("static", filename="css/login.css")
    loginButton = url_for("static", filename="images/loginButton.jpg")
    login = url_for("static", filename="images/login.jpg")
    return render_template("index.html",indexCss = indexCss,loginCss=loginCss,loginButton=loginButton,login=login)

@app.route('/login',methods=['GET'])
def login_get():
    form = request.form
    username = form.get('username')
    password = form.get('password')

    r = requests.get('http://127.0.0.1:8080/login', params={'username': username, 'password':password})
    return r.text



if __name__ == '__main__':
    app.run()
