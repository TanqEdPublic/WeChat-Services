from flask import Flask, url_for, render_template, request, json, flash, redirect
import requests
import datetime

app = Flask(__name__)
app.secret_key = "123"


@app.route('/')
def index():
    return render_template("index.html")


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
    if not username:
        flash("please input username !")
    elif not password:
        flash("please input password !")
    elif(re == 'logged'):
        return redirect("/public-chat")
    else:
        flash("username/password is wrong!")

    return render_template("index.html")


@app.route('/register')
def register_init():
    return render_template("register.html")


def ServerRegister(user,psd):
    params = {
        'username': user,
        'password': psd
    }
    url = 'http://34.251.207.109:8080/sign-up'
    r = requests.post(url, params).text
    return r


@app.route('/register', methods=['POST'])
def register():
    form = request.form
    username = form.get('username')
    password = form.get('password')
    re = ServerRegister(username, password)
    if not username:
        flash("please input username !")
    elif not password:
        flash("please input password !")
    elif(re == 'duplicate_user'):
        flash('ERROR: user exist!')
    elif(re == 'registered'):
        flash('register success!')

    return render_template("register.html")


@app.route('/public-chat')
def chatting():
    return render_template("publicChat.html")

@app.route('/public-chat', methods=['POST'])
def sendMessage():
    form = request.form
    msg = form.get('sendmessage')
    url = 'http://34.251.207.109:8080/chatroom/send-msg'
    now = datetime.datetime.now()
    time = now.strftime("%Y-%m-%d %H:%M:%S")
    data = {'username': 'Kyle', 'date': time, 'room': 'public', 'message': msg}
    r = requests.post(url, json = data).text
    displayMeaaage = time+"  Kyle: \n"+msg
    if(r == 'success'):
        return render_template("publicChat.html",messages = displayMeaaage)
    else:
        return "error"


def chatting():
    return render_template("publicChat.html")

if __name__ == '__main__':
    app.debug = True
    app.run()
