from flask import Flask
from markupsafe import escape

app = Flask(__name__)


@app.route('/<username>')
def show_user_profile(username):
    # show the user profile for that user
    print("Hit", username)
    return '%s' % escape(username)

app.run(host="0.0.0.0", port=5002)
