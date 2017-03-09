from flask import Flask, url_for, render_template, request, json
import requests
from celery import Celery
import time

app = Flask(__name__)


@app.route('/')
def index():
    loginButton = url_for("static", filename="images/loginButton.jpg")
    login = url_for("static", filename="images/login.jpg")
    return render_template("index.html",loginButton=loginButton,login=login)

def ServerLogin(user,psd):
    params = {
        'username': user,
        'password': psd
    }
    url = 'http://34.251.207.109:8080/login'

    r = requests.get(url, params).text
    return r

@app.route('/log_in',methods=['POST'])
def login_get():
    form = request.form
    username = form.get('username')
    password = form.get('password')
    re = ServerLogin(username,password)
    return re



if __name__ == '__main__':
    app.run()
